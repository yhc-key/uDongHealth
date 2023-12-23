<template>
    <div id="carouselExampleAutoplaying" class="carousel slide" data-bs-ride="carousel">
        <div class="carousel-inner text-center ">
            <div class="carousel-item active justify-content-center">
                <img :src="`src/${firstImage.src}`" alt="image" height="400" width="500">
                <p>{{ firstImage.alt }}</p>
            </div>
            <div class="carousel-item justify-content-center" v-for="(image, index) in pImgs" :key="image.alt">
                <img :src="`src/${image.src}`" height="400" width="500" :alt="`${image.alt}`">
                <p>{{ image.alt }}</p>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleAutoplaying"
            data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
    </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import _ from 'lodash';
const firstImage = ref({});
const images = ref([]);
const pImgs = ref([]);
const prop = defineProps({
    fitPart: String
})

onMounted(() => {
    if (prop.fitPart === "All") {
        images.value = upperBodyImages.concat(lowerBodyImages).concat(coreImages)

    } else if (prop.fitPart === "upperBody") {
        images.value = upperBodyImages
    } else if (prop.fitPart === "lowerBody") {
        images.value = lowerBodyImages
    } else {
        images.value = coreImages
    }
    firstImage.value = _.cloneDeep(images.value[0]);
    pImgs.value = _.cloneDeep(images.value).slice(1);
})

const upperBodyImages = [{
    src: "assets/상체/바웨이트.jpg",
    alt: "바웨이트",
}, {
    src: "assets/상체/상체근육풀기.jpg",
    alt: "상체근육풀기"
}, {
    src: "assets/상체/어깨근육풀기.jpg",
    alt: "어깨근육풀기",
}, {
    src: "assets/상체/역기내리기.jpg",
    alt: "역기내리기",
}, {
    src: "assets/상체/역기올리기.jpg",
    alt: "역기올리기",
}, {
    src: "assets/상체/터닝암.jpg",
    alt: "터닝암",
}, {
    src: "assets/상체/트윈터닝암.jpg",
    alt: "트윈터닝암",
}, {
    src: "assets/상체/팔어깨근육풀기.jpg",
    alt: "팔어깨근육풀기",
}, {
    src: "assets/상체/풀웨이트.jpg",
    alt: "핸드웨이트",
},];
const lowerBodyImages = [{
    src: "assets/하체/다리뻗기.jpg",
    alt: "다리뻗기",
}, {
    src: "assets/하체/레그스트레칭.jpg",
    alt: "레그스트레칭",
}, {
    src: "assets/하체/레그프레스.jpg",
    alt: "레그프레스",
}, {
    src: "assets/하체/워킹싸이클론.jpg",
    alt: "워킹싸이클론",
}, {
    src: "assets/하체/크로스워킹.jpg",
    alt: "크로스워킹",
}, {
    src: "assets/하체/하늘걷기.jpg",
    alt: "하늘걷기",
}];
const coreImages = [{
    src: "assets/복부/롤링웨이브.jpg",
    alt: "롤링웨이브",
}, {
    src: "assets/복부/롤스트레칭.jpg",
    alt: "롤스트레칭",
}, {
    src: "assets/복부/바디싣업.jpg",
    alt: "바디싣업",
}, {
    src: "assets/복부/옆파도타기.jpg",
    alt: "옆파도타기",
}, {
    src: "assets/복부/윗몸일으키기.jpg",
    alt: "윗몸일으키기",
}, {
    src: "assets/복부/트위스트.jpg",
    alt: "트위스트",
}, {
    src: "assets/복부/허리돌리기.jpg",
    alt: "허리돌리기",
}, {
    src: "assets/복부/허리등안마기.jpg",
    alt: "허리등안마기",
}, {
    src: "assets/복부/허리스트레칭.jpg",
    alt: "허리스트레칭",
},];

</script>

<style scoped>
.carousel-control-prev-icon,
.carousel-control-next-icon
{
    color: black;
    background-color: black;
}



p{
    font-family: 'Pretendard-Regular';
    font-weight: 600;
    font-size: 1rem;
}


</style>