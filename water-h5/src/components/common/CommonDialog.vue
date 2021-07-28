<template>
  <el-dialog
    :title="title"
    v-loading="dialogLoading"
    element-loading-spinner="el-icon-loading"
    :element-loading-text="loadingText"
    :visible.sync="dialogVisible"
    :width="width"
    :before-close="handleClose"
  >
    <slot></slot>
    <span slot="footer" class="dialog-footer">
      <el-button size="small" @click.native="cancelHandle">取消</el-button>
      <el-button size="small" @click.native="handleSubmit">确认</el-button>
    </span>
  </el-dialog>
</template>

<script>
export default {
  name: "common-dialog",
  props: {
    dialogVisible: {
      type: Boolean,
      default: false,
    },
    dialogLoading: {
      type: Boolean,
      default: false,
    },
    width: {
      type: String,
      default: "40%",
    },
    title: {
      type: String,
      default: "",
    },
    loadingText: {
      type: String,
      default: "拼命加载中",
    },
  },
  methods: {
    handleSubmit() {
      this.$emit("handleSubmit");
    },
    cancelHandle() {
      this.$emit("cancelHandle", false);
    },
    handleClose() {
      this.$emit("cancelHandle", false);
    },
  },
};
</script>

<style>
</style>