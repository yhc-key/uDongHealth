<template>
    <div class="container">
    <div class="row d-flex">
        <div class="col images hover-effect" @click="openNewWindow(`yhC-key`)">
            <img src="../assets/yhC.png" width="280" class="hover-effect"/>
           
        </div>

        <div class="col images" @click="openNewWindow(`YuKyung-Chung`)">
            <img src="../assets/ykC.png" width="280" class="hover-effect"/>
        </div>

    </div>
</div>
</template>

<script setup>


const openNewWindow = (id) => {
    window.open(`https://github.com/${id}`);
}


</script>

<style scoped>

.images {
    display : flex;
    justify-content: center;
    align-items: center;
    height: 77.5vh;


}
.hover-effect:hover {
    cursor: pointer;
}

</style>