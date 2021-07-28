<template>
  <common-dialog
    :dialogVisible.sync="dialogVisible"
    :width="width"
    :title="title"
    @handleSumit="handleSubmit"
    @cancelHandle="cancelHandle"
  >
  </common-dialog>
</template>

<script>
import CommonDialog from "./common/CommonDialog.vue";
export default {
  components: {
    CommonDialog,
  },
  methods: {
    handleSubmit() {
      this.$emit("handleSubmit");
    },
    cancelHandle() {
      this.$emit("cancelHandle");
    },
  },
};
</script>

<style>
</style>