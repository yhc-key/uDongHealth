<template>
    <div class="container mx-5">
        <div class="nav nav-underline mx-5" id="search-nav">
            <RouterLink class="nav-link" :class="{ 'active': $route.path === '/search/place' }" to="/search/place">일반 검색</RouterLink>
            <RouterLink class="nav-link" :class="{ 'active': $route.path === '/search/fit' }" to="/search/fit">부위 검색</RouterLink>
            <RouterView />
        </div>

        <div class="mx-5">
            <div v-if="placeStore.fitSearchTF === false">
                <h3 class="my-3">일반 검색 결과</h3>
                <table class="table table-hover align-middle">
                    <thead>
                        <tr>
                            <th>지역구</th>
                            <th>동</th>
                            <th>상세주소</th>
                            <th>즐겨찾기</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <tr v-for="place in placeStore.limitStores.value" :key="place.placeId">
                            <td class="text-center">{{ place.addressGu }}</td>
                            <td>{{ place.addressDong }}</td>
                            <td class="addressDetail" @click="goDetail(place.placeId)">{{ place.addressDetail }}</td>
                            <td><button class="btn" @click.stop.prevent=addFav(place.placeId)>⭐</button></td>
                        </tr>
                    </tbody>
                </table> 
            </div>
            <div v-else>
                <h3 class="my-3">부위({{ placeStore.fitSearchName }}) 검색 결과</h3>
                <table class="table table-hover align-middle">
                    <thead>
                        <tr>
                            <th class="text-center col-1" >지역구</th>
                            <th class="text-center col-1" >동</th>
                            <th class="text-center col-2" >공원명</th>
                            <th class="text-center col-1" >종류 수</th>
                            <th class="text-center col-3">설치운동기구 종류</th>
                            <!-- <th class="text-center col-2">상세보기</th> -->
                            <th class="text-center col-2">즐겨찾기</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                        <tr v-for="place in placeStore.searchPlaces.value" :key="place.placeId">
                            <td>{{ place.addressGu }}</td>
                            <td>{{ place.addressDong }}</td>
                            <td class="addressDetail" @click="goDetail(place.placeId)">{{ place.공원명 }}</td>
                            <td>{{ place.cnt }}</td>
                            <td>{{ place.설치운동기구종류 }}</td>
                            <!-- <td class="text-center"><button class="btn btn-primary" @click="goDetail(place.placeId)" :placeId="place.placeId">상세보기</button>
                            </td> -->
                            <td><button class="btn"  @click.stop.prevent=addFav(place.placeId)>⭐</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>
<script setup>
import { ref, onMounted } from 'vue'
import { RouterLink, RouterView, useRouter } from 'vue-router'
import { usePlaceStore } from '../stores/place';
import { useUserStore } from '../stores/user';
import axios from 'axios';
const userStore = useUserStore();
const placeStore = usePlaceStore();

const router = useRouter();
const user = ref({});

//상세주소 클릭 시 goDetail로 보내서 처리
const goDetail = (placeId) => {
    router.push({ name: 'placeDetail', params: { placeId: placeId } })
}



//찜 기능 유효성 검사
const addFav = async (placeId) => {
    if (userStore.loginTF === false) {
        alert("찜 기능을 사용할려면 로그인 해주세요")
        return;
    }

    if (userStore.favCount >=4) {
        alert("찜 등록은 4개까지만 가능합니다.")
        return;
    }

    const URL = import.meta.env.VITE_APP_API_FAVORITES_URL + `/${user.value.userId}/${placeId}`

    try {
        const response = await axios.post(URL)
        alert("찜 등록에 성공했습니다.")
        userStore.addFavTF = true;
    }
    catch (error) {
        alert(error.response.data.message);
    }


}

//초기 렌더링시 해당 되는 장소가 20개이상이면 20개로 고정, 아니면 그대로
onMounted(async () => {
    router.push("/search/place")
    await placeStore.getPlaces();
    if (placeStore.places.length >= 20) {
        placeStore.limitStores.value = placeStore.places.slice(0, 20);
    } else {
        placeStore.limitStores.value = placeStore.places;
    }
    user.value = JSON.parse(sessionStorage.getItem("user"));

})

</script>

<style scoped>

/* active 클래스로 선택된 링크의 색상 변경 */
#search-nav .nav-link.active {
  color: #0D6EFD;
  font-weight: 800;
}
#search-nav a{
    text-decoration-line: none;
    color: black;
    font-family: 'Pretendard-Regular';
    font-weight: 800;
}

table{
    text-align: center;
}

h3{
    font-family: 'Pretendard-Regular';
    font-weight: 800;
}

.active{
    color: #0D6EFD !important;
}

.addressDetail {
    color : #4DA350;
}
.addressDetail:hover {
    color: #0D6EFD; 
    cursor : pointer;
}
.btn {
    font-size:large
}
</style>