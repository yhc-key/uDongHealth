<template>
    <div>
        <table class="table" border="1">
            <thead>
                <tr>
                    <th>작성자</th>
                    <th>내용</th>
                    <th>조회수</th>
                    <th></th>
                    <th></th>
                </tr>
            </thead>
            <tbody class="table-group-divider">
                <tr v-for="review in reviews" :key="review.reviewId">
                    <td>{{ review.writer }}</td>
                    <td>{{ review.content }}</td>
                    <td>{{ review.viewCnt }}</td>

                    <td><button class="btn btn-success" @click="goReviewEdit(review)" :reviewId="review.reviewId">수정</button></td>
                    <td><button class="btn btn-danger" @click="goReviewDelete(review)" :reviewId="review.reviewId">삭제</button></td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import { usePlaceStore } from '../../stores/place';
import { useUserStore } from '../../stores/user';

const route = useRoute();
const router = useRouter();
const reviews = ref([]);
const userStore = useUserStore();
const placeStore = usePlaceStore();

//review 추가, 유효성 검사
const goReviewEdit = (review) => {
    if (userStore.loginTF === false) {
        alert("로그인을 먼저 해주세요")
        return router.push("/")

    } else if(review.userId !== JSON.parse(sessionStorage.getItem("user")).userId) {
        alert("본인이 작성한 리뷰만 수정 가능합니다.")
        return router.push("/")
    } 
    else {
        
        return router.push({ name: 'reviewEdit', params: { reviewId: review.reviewId, content : review.content } });
    }
}   

//리뷰삭제, 해당 아이디만 삭제할 수 있도록 미리 체크 후 api 통신
const goReviewDelete = async (review) => {
    if (review.userId === JSON.parse(sessionStorage.getItem("user")).userId) {
        try {
            let URL = import.meta.env.VITE_APP_API_REVIEW_URL + "/" + placeStore.reviewPlaceId + "/" + review.reviewId
            let response = await axios.delete(URL);
            URL = import.meta.env.VITE_APP_API_REVIEW_URL + "/" + route.params.placeId
            response = await axios.get(URL);
            reviews.value = response.data;
            alert("삭제 완료되었습니다.")
        } catch (error) {
            console.log(error);
        }
    } else {
        alert("본인이 작성한 리뷰만 삭제 가능합니다.")
    }
}

//placeDetail 접근 시 해당파일 리뷰 가져오기
onMounted(async () => {
    let URL = import.meta.env.VITE_APP_API_REVIEW_URL + "/" + route.params.placeId
    const response = await axios.get(URL);
    reviews.value = response.data;
})
//placeDetail/:id -> placeDetail/:id로 이동할 때 렌더링안되는 문제 해결용
watch(()=> route.params.placeId, async(newParam, oldParam) => {
    let URL = import.meta.env.VITE_APP_API_REVIEW_URL + "/" + route.params.placeId
    const response = await axios.get(URL);
    reviews.value = response.data;
})

</script>

<style scoped>
.btn-success {
    background-color : #2D7E32;
    border-color : #2D7E32;
}
</style>