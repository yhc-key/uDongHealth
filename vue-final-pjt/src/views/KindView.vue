<template>
    <div class="mx-5 kindView">
        <h2 class="mx-5 text-center">운동기구 종류</h2>
        <div class="nav nav-underline d-flex justify-content-center">
            <button type="button" class="nav-link" :class="{ 'active': fitPart === 'All' }" @click.stop.prevent="handleFitPartClick(`All`)">전체</button>
            <button type="button" class="nav-link" :class="{ 'active': fitPart === 'upperBody' }" @click.stop.prevent="handleFitPartClick(`upperBody`)">상체</button>
            <button type="button" class="nav-link" :class="{ 'active': fitPart === 'lowerBody' }" @click.stop.prevent="handleFitPartClick(`lowerBody`)">하체</button>
            <button type="button" class="nav-link" :class="{ 'active': fitPart === 'core' }" @click.stop.prevent="handleFitPartClick(`core`)">복부</button>
        </div>
        <div class="text-center">
            <div v-if="fitPart === 'All'">
                <TheKindCarousel :fitPart="'All'" />
            </div>
            <div v-else-if="fitPart === 'upperBody'">
                <TheKindCarousel :fitPart="'upperBody'" />
            </div>
            <div v-else-if="fitPart === 'lowerBody'">
                <TheKindCarousel :fitPart="'lowerBody'" />
            </div>
            <div v-else>
                <TheKindCarousel :fitPart="'core'" />
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import _ from 'lodash';
import { useRouter } from 'vue-router'
import TheKindCarousel from '../components/common/TheKindCarousel.vue'
const firstImage = ref({});
const fitPart = ref("All")
const images = ref([]);
const pImgs = ref([]);

const handleFitPartClick = (fitname) => {
    fitPart.value = fitname;

}

</script>

<style scoped>
h2{
    font-family: 'Pretendard-Regular';
    font-weight: 800;
}
.kindView {
    height : 77.5vh;
}

button{
    font-family: 'Pretendard-Regular';
    font-weight: 500;
    font-size: 1rem;
    color: black;
    text-align: center;
    justify-content: center;
    align-items: center;
}
.active{
    color: #0D6EFD !important;
}

</style>
