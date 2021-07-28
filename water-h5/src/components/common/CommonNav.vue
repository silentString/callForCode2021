<template>
  <van-nav-bar
    :title="title"
    :fixed="fixed"
    :border="border"
    left-arrow
    @click-left="onClickLeft"
  />
</template>

<script>
import { NavBar } from "vant";
export default {
  components: {
    [NavBar.name]: NavBar,
  },
  props: {
    title: {
      type: String,
      default: "",
    },
    border: {
      type: Boolean,
      default: false,
    },
    fixed: {
      type: Boolean,
      default: false,
    },
  },
  methods: {
    onClickLeft() {
      this.$emit("onClickLeft");
    },
  },
};
</script>

<style>
</style>