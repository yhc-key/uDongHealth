<template>
    <!-- Navigation-->
    <nav class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
        <div class="container px-2 px-lg-2 zNav">
            <div class="logo">
                <RouterLink to="/"> <img src="@/assets/logo.png" alt="Logo" width="50" height="50"
                        class="d-inline-block align-text-top">
                </RouterLink>


                <RouterLink to="/">
                    <h1 class="title">Udong Health</h1>
                </RouterLink>

                <button class="navbar-toggler navbar-toggler-right" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false"
                    aria-label="Toggle navigation">
                    Menu
                    <i class="fas fa-bars"></i>
                </button>
            </div>

            <div class="collapse navbar-collapse" id="navbarResponsive">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <RouterLink to="/" class="nav-link">Home</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink to="/about" class="nav-link">About us</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink to="/search" class="nav-link">Search</RouterLink>
                    </li>
                    <li class="nav-item">
                        <RouterLink to="/kind" class="nav-link">Kind</RouterLink>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
</template>

<script setup>
import { onMounted } from 'vue'
import { useRouter } from 'vue-router'


const router = useRouter();

onMounted(() => {
    router.replace("/");
})


</script>

<style  scoped>
#mainNav{
    width: 100%;
    background-color: #67BA6A;
    background: linear-gradient(to center, #A6D7A7 10%, #449F47 50%);
    z-index : 1;
}

#navbarResponsive{
    font-size: 18px;
}
.logo {
    display: flex;
    align-items: center;
    margin: 10px;
}

img {
    margin-right: 10px;
}

.title {
    font-weight: 700;
    font-size: 25px;
    text-align: left;
}
.zNav {
    z-index : 3;
}



a {
    text-decoration-line: none;
    color: white;
    font-family: "Pretendard-Regular";
}
</style>