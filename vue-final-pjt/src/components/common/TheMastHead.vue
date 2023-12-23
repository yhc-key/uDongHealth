<template>
  <!-- Masthead-->
  <header class="masthead">
    <video autoplay muted loop class="video-bg">
      <source src="@/assets/walking.mp4" type="video/mp4" />
      Your browser does not support the video tag.
    </video>
    <!-- Content inside the header -->
    <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
      <div class="d-flex justify-content-center">
        <div class="text-center">
          <h1 class="mx-auto my-0 text-uppercase">우리 동네 무료 헬스장</h1>
          <h2 class="text-white-50 mx-auto mt-2 mb-5">근처에 이용할 수 있는 운동시설 조회하고 리뷰를 공유하세요.</h2>
          <button class="btn btn-primary" @click="goToHome">근처 둘러보기</button>
        </div>
      </div>
    </div>
  </header>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter();

const goToHome = () => {
  const section1 = document.getElementById('rootSection');
  if (section1) {
    section1.scrollIntoView({ behavior: 'smooth' });
  }
}

</script>

<style  scoped>

.video-bg {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover;
  z-index: -1;
}
.masthead {
  position: relative;
  width: 100%;
  height: auto;
  min-height: 35rem;
  padding: 15rem 0;
  background: linear-gradient(to bottom, rgba(0, 0, 0, 0.3) 0%, rgba(0, 0, 0, 0.7) 75%, #000 100%), url("@/assets/walking.mp4");
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: scroll;
  background-size: cover;
  z-index: 2;
}

.masthead h1 {
  font-family: "JalnanGothic";
  font-size: 40px;
  line-height: 3rem;
  background: linear-gradient(rgba(255, 255, 255, 0.9), rgba(255, 255, 255, 0));
  -webkit-text-fill-color: transparent;
  -webkit-background-clip: text;
  background-clip: text;
}

.masthead h2,
.masthead .h2 {
  max-width: 20rem;
  font-size: 1rem;
  font-family: "Pretendard-Regular";
}

@media (min-width: 768px) {

  .masthead h1,
  .masthead .h1 {
    font-size: 4rem;
    line-height: 4rem;
  }
}

@media (min-width: 992px) {
  .masthead {
    height: 100vh;
    padding: 0;
  }

  .masthead h1,
  .masthead .h1 {
    font-size: 90px;
    line-height: 6.5rem;
  }

  .masthead h2,
  .masthead .h2 {
    max-width: 30rem;
    font-size: 1.25rem;
  }
}

.navbar {
  flex: content;
}

.main-container {
  color: whitesmoke;
  font-family: 'Pretendard-Regular';
}

.logo {
  display: flex;
  align-items: center;
  margin: 10px;
}

img {
  margin-right: 10px;
}


.nav-container {
  /* margin-left: auto; */
  font-weight: 700;
  /* align-items: end; */
}

.title {
  font-weight: 700;
  font-size: 25px;
  text-align: left;
}

a {
  font-family: 'Pretendard-Regular';
  text-decoration-line: none;
  color: whitesmoke;
}

.btn-primary {
  background-color: #2D7E32;
  border-color: #2D7E32;
}

.mainTitle {
  margin-top: 30px;
  display: inline-block;
  background: linear-gradient(to top, #54ddff 20%, transparent 30%);
  font-family: 'JalnanGothic';
}</style>