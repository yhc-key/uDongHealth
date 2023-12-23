
<template>
    <div id="reviewEdit">
        <div class="form-container col-9">
            <label for="reviewContent" class="form-label mb-3">리뷰 수정</label>
            <textarea v-model="content" class="form-control mb-3" id="reviewContent" rows="3"
                placeholder="내용을 입력하세요."></textarea>
            <div class="d-flex align-items-end flex-column">
                <button type="submit" class="btn btn-primary mb-3" @click.stop.prevent=reviewEdit
                    @keyup.enter.stop.prevent=reviewEdite>수정</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useUserStore } from '../../stores/user';
import { usePlaceStore } from '../../stores/place';
import axios from 'axios'
import router from '../../router';
import { useRoute } from 'vue-router';
const userStore = useUserStore();
const placeStore = usePlaceStore();
const content = ref("");
const route = useRoute();
const reviewId = ref("");
const reviewEdit = async () => {
    const URL = import.meta.env.VITE_APP_API_REVIEW_URL + `/${placeStore.reviewPlaceId}/${reviewId.value}`;
    console.log(URL);
    try {
        const response = await axios.put(URL, content.value
        );
        alert("수정이 완료되었습니다.")

    } catch (error) {
        alert("문제가 발생했습니다. 관리자에게 문의하세요.");
    }

    router.replace(`/placeDetail/${placeStore.reviewPlaceId}`)

}



onMounted(() => {
    reviewId.value = route.params.reviewId
    content.value = route.params.content

})

</script>

<style scoped>
.form-container {
    font-family: 'Pretendard-Regular';
}

.form-label {
    font-size: 1.5rem;
    font-weight: 600;
}
#reviewEdit {
    height: 77.5vh;
}
</style>