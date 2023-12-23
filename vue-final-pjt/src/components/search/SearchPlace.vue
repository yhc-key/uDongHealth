<template>
    
    <div class="input-group mb-3 my-3">

    <select class="form-select custom-select" v-model="searchCondition">
        <option value="place_name">공원이름</option>
        <option value="address_dong">지역(동)</option>
    </select>
      <input type="text" class="form-control" id="search" v-model="keyword" placeholder="검색할 단어를 입력하세요."/>
      <button class="btn btn-primary searchPlaceButton" type="submit" @keyup.enter.stop.prevent=searchPlace
            @click.stop.prevent=searchPlace>검색</button>
    </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { usePlaceStore } from '../../stores/place';
import axios from 'axios'

const placeStore = usePlaceStore();
const keyword = ref("");
const searchCondition = ref("place_name");

const searchPlace = async () => {
    const URL = import.meta.env.VITE_APP_API_PLACE_URL + "/search";
    console.log(URL);
    console.log(keyword.value);
    console.log(searchCondition.value);
    try {
        const response = await axios.get(URL, {
            params: {
                key: searchCondition.value,
                word: keyword.value,
            }

        })
        placeStore.limitStores.value = response.data;
        console.log(placeStore.limitStores.value);
        keyword.value = "";
    }
    catch (error) {
        console.log(error);

    }
}   

onMounted (() => {
    placeStore.fitSearchTF = false;
})
</script>

<style scoped>
.custom-select {
    max-width: 150px; /* 원하는 가로 크기 설정 */
}

.searchPlaceButton {
    z-index: 0;
}

</style>