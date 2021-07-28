<template>
  <div id="app">
    <router-view v-if="isAlive"></router-view>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      isAlive: true,
    };
  },
};
</script>

<style>
</style>
