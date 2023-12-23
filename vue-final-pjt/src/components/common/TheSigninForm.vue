<template>
    <div class="loginCard">
        <h4 class="hiUser">🌱{{ user.name }}님 반갑습니다.</h4>
        <p> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 오늘은 어디로 운동을 가실 건가요?</p>
    </div>
    <br>
    <div class="d-flex justify-content-end">
        <button class="btn btn-secondary mb-3" @click.stop.prevent=logout>로그아웃</button>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import router from '../../router';
import { useUserStore } from '@/stores/user'


const store = useUserStore();
const user = ref({});

const logout = () => {

    store.loginTF = false;
    sessionStorage.removeItem("user")
    alert("로그아웃 되었습니다")
    router.replace("/")

}


onMounted(() => {
    user.value = JSON.parse(sessionStorage.getItem("user"));
})




</script>

<style scoped>
.loginCard {
    font-family: 'Pretendard-Regular';
}

.hiUser {
    font-weight: 700;
}
</style>