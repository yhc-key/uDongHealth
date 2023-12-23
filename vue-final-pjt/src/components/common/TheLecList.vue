<template>
    <div class="list-container mx-5">
        <ol class="list-group list-group-numbered  px-2 py-2">
            <li class="list-group-item d-flex justify-content-between align-items-start p-4" v-for="lec in lecList"
                :key=lec.placeId>
                <div class="ms-2 me-auto">
                    <RouterLink :to="{ path: `/placeDetail/${lec.placeId}` }"> {{ lec.공원명 }} </RouterLink>
                    <br>
                    {{ lec.설치주소 }}
                    <br>
                    {{ lec.distance }} km
                </div>
                <span class="badge rounded-pill" @click.stop.prevent=addFav(lec.placeId)>⭐</span>
            </li>
        </ol>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { RouterLink } from 'vue-router'
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
const lecList = ref([]);


const addFav = async (placeId) => {
    if (userStore.loginTF === false) {
        alert("찜 기능을 사용할려면 로그인 해주세요")
        return;
    }

    if (userStore.favCount >= 4) {
        alert("찜 등록은 4개까지만 가능합니다.")
        return;
    }

    const URL = import.meta.env.VITE_APP_API_FAVORITES_URL + `/${userStore.user.value.userId}/${placeId}`

    try {
        const response = await axios.post(URL)
        alert("찜 등록에 성공했습니다.")
        userStore.addFavTF = true;
    }
    catch (error) {
        alert(error.response.data.message);
    }

}

onMounted(async () => {
    const lon = 127.0396029
    const lat = 37.501286
    const URL = import.meta.env.VITE_APP_API_PLACE_URL + "/find/" + lat + "/" + lon;

    axios.get(URL).then((res) =>
        lecList.value = res.data).catch((error) => console.log(error));

    userStore.user.value = JSON.parse(sessionStorage.getItem("user"));
})



</script>

<style scoped>
.list-container {
    font-family: 'Pretendard-Regular';
}

a {
    text-decoration: none;
    /* color: black; */
    font-family: 'Pretendard-Regular';
}

.badge {
    font-size: large;
}

.badge:hover {
    cursor: pointer;
}</style>