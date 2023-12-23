
<template>
    <div id="reviewAdd">

        <div class="form-container col-9">
            <label for="content" class="form-label mb-3">리뷰 등록</label>
            <textarea v-model="content" class="form-control mb-3" id="content" rows="3" placeholder="내용을 입력하세요."></textarea>
            <div class="d-flex align-items-end flex-column">
                <button type="submit" class="btn btn-primary mb-3" @click.stop.prevent=reviewCreate
                    @keyup.enter.stop.prevent=reviewCreate>등록</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { usePlaceStore } from '../../stores/place';
import axios from 'axios';
import router from '../../router';

const user = ref({});
const content = ref("");
const store = usePlaceStore();
const review = ref({});

const reviewCreate = async () => {
    if (content.value === "") {
        alert("빈 내용을 등록할 수 없습니다.");
        return;
    }
    const URL = import.meta.env.VITE_APP_API_REVIEW_URL + `/${store.reviewPlaceId}`
    console.log(URL);
    try {
        const response = await axios.post(URL, {
            userId: user.value.userId,
            placeId: store.reviewPlaceId,
            writer: user.value.name,
            content: content.value,
        })
        alert("등록이 완료되었습니다.");

    } catch (error) {
        console.log(error);
        alert("문제가 발생했습니다. 관리자에게 문의해주세요.");
    }

    router.replace(`/placeDetail/${store.reviewPlaceId}`)
}



// store.reviewPlaceId


onMounted(() => {
    user.value = JSON.parse(sessionStorage.getItem("user"));
})
</script>

<style scoped>
.form-container {
    font-family: 'Pretendard-Regular';
}

#reviewAdd {
    height: 77.5vh;
}

.form-label {
    font-size: 1.5rem;
    font-weight: 600;
}
</style>