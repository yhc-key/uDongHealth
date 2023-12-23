<template>
    <div class="favListClass">
        <div v-if="userStore.loginTF === false" class="icon-text-container row">
            <h4 class="mt-3"> 오늘의 운동 상식 </h4>
            <div v-for="art in arts" @click="goArticle(art.link)">
                <div class="card mb-3" style="width: 18rem;">
                    <img :src="`${art.src}`" class="card-img-top " alt="...">
                    <div class="card-body">
                        <p class="card-text">{{ art.content }}</p>
                    </div>
                </div>
            </div>
        </div>
        <div v-else>
            <div v-if="favList.length === 0">
                <p>아직 찜 목록이 없습니다.</p>
            </div>
            <div v-else>
                <h3> 즐겨찾는 장소</h3>
                <div v-for="fav in favList"
                    class="p-3 my-3 border-bottom border-end  border-2 border-secondary shadow rounded">
                    <RouterLink :to="`/placeDetail/${fav.placeId}`">
                        <h5>{{ fav.addressGu }} {{ fav.addressDong }} {{ fav.공원명 }}</h5>
                    </RouterLink>
                    <p> {{ fav.설치운동기구종류 }}</p>
                    <div class="d-flex justify-content-end">
                        <button class="btn btn-danger" @click="deleteFav(fav.placeId)">삭제</button>
                    </div>
                </div>

            </div>
        </div>

    </div>
</template>

<script setup>
import { ref, watch } from 'vue'
import { useUserStore } from '../../stores/user';
import axios from 'axios';
const userStore = useUserStore();
const favList = ref([]);
const user = ref({});
const goArticle = (link) => {
    window.open(link);
}

const deleteFav = async (placeId) => {
    const deleteURL = import.meta.env.VITE_APP_API_FAVORITES_URL + `/${user.value.userId}/${placeId}`
    try {
        await axios.delete(deleteURL);
        userStore.favCount--;
        favList.value = favList.value.filter(item => item.placeId !== placeId);
    } catch (error) {
        console.log(error);
    }
}


watch([() => userStore.loginTF, () => userStore.addFavTF], async () => {
    if (userStore.loginTF === true) {
        user.value = JSON.parse(sessionStorage.getItem("user"));

        const URL = import.meta.env.VITE_APP_API_FAVORITES_URL + `/${user.value.userId}`;
        try {
            const response = await axios.get(URL);
            favList.value = response.data;
            userStore.favCount = favList.value.length;
            if (userStore.favCount > 0) {
                favList.value.forEach((item) => {
                    const arr = item.설치운동기구종류.split(",");
                    if (arr.length >= 3) {
                        item.설치운동기구종류 = `${arr[0]}, ${arr[1]}, ${arr[2]}, 등...`
                    }
                })
            }
        } catch (error) {
            console.error('Error fetching data:', error);
        }

    } else {
        favList.value = [];
    }

    // addFavTF가 true일 때만 수행하도록 변경
    if (userStore.addFavTF) {
        userStore.addFavTF = false;
    }
});

const arts = [{
    link: "https://health.chosun.com/site/data/html_dir/2022/06/13/2022061301799.html",
    src: "https://health.chosun.com/site/data/img_dir/2022/06/13/2022061301794_0.jpg",
    content: "'운린이'가 알아야 하는, 운동 상식 3가지",

}, {
    link: "https://kormedi.com/1359474/%ED%86%B5%EC%A6%9D-%EC%9E%88%EC%96%B4%EC%95%BC-%ED%9A%A8%EA%B3%BC-%EC%9E%98%EB%AA%BB%EB%90%9C-%EC%9A%B4%EB%8F%99-%EC%83%81%EC%8B%9D-4/   ",
    src: "https://cdn.kormedi.com/wp-content/uploads/2021/10/211007_01_01.jpg",
    content: "통증 있어야 효과? 잘못된 운동 상식 4",
}]


</script>

<style scoped>
.sameLine {
    color: black;
}

.icon-text-container {
    display: flex;
    align-items: center;
}

p {
    margin-bottom: 4px
}

.writer {
    font-weight: 700;
    color: #1E88E5
}

a {
    text-decoration: none;
}

.card:hover {
    cursor: pointer;
}

.favListClass {
    font-family: 'Pretendard-Regular';
}

h4 {
    font-family: 'Pretendard-Regular';
    font-weight: 700;
}
</style>