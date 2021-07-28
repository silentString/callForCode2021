<template>
  <div>
    <common-nav
      :title="navTitle"
      :fixed="navFixed"
      :border="navBorder"
      @onClickLeft="onClickLeft"
    />
    <div class="fail-content">
      <div class="fail-header">
        <img src="../assets/submit-error.png" alt="" />
        <div class="fail-title">提交失败</div>
        <div class="fail-tip">购买净水设备-购买滤水器购买滤水器</div>
      </div>

      <div class="fail-footer">
        <div class="fail-btn" @click="goToHome">
          <span>返回首页</span>
        </div>
        <div class="restart" @click="goToRestart">
          <span>重新申请</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommonNav from "../components/common/CommonNav.vue";
export default {
  components: {
    CommonNav,
  },
  data() {
    return {
      navTitle: "",
      navFixed: true,
      navBorder: false,
    };
  },
  methods: {
    onClickLeft() {
      this.$router.push("/addRecord");
    },
    goToHome() {
      this.$router.push("/");
    },
    goToRestart() {
      this.$router.push("/addRecord");
    },
  },
};
</script>

<style lang="less" scoped>
.fail-content {
  margin-top: 46px;
  .fail-header {
    display: flex;
    align-items: center;
    justify-content: center;
    flex-flow: column;
    margin: 100px auto 60px;
    img {
      width: 100px;
      height: 100px;
    }
    .fail-title {
      font-family: PingFangSC-Medium;
      font-size: 20px;
      color: #2a2a3d;
      letter-spacing: 0;
      text-align: center;
      line-height: 48px;
      font-weight: 700;
    }
    .fail-tip {
      font-family: PingFangSC-Regular;
      font-size: 14px;
      color: #2a2a3d;
      text-align: center;
      line-height: 14px;
      font-weight: 400;
    }
  }
  .fail-footer {
    display: flex;
    justify-content: space-around;
    .fail-btn {
      width: 156px;
      height: 44px;
      text-align: center;
      line-height: 36px;
      border: 1px solid #2d70ed;
      border-radius: 4px;
      cursor: pointer;
      span {
        font-family: PingFangSC-Medium;
        font-size: 16px;
        color: #2d70ed;
        text-align: center;
        font-weight: 500;
      }
    }
    .restart {
      width: 156px;
      height: 44px;
      background-image: linear-gradient(134deg, #77baff 0%, #3590ed 100%);
      border-radius: 4px;
      text-align: center;
      line-height: 36px;
      cursor: pointer;
      span {
        font-family: PingFangSC-Medium;
        font-size: 16px;
        color: #ffffff;
        text-align: center;
        font-weight: 700;
      }
    }
  }
}

/deep/ .van-nav-bar__title {
  color: #202044;
  font-weight: 600;
  font-size: 18px;
}
/deep/ .van-nav-bar .van-icon {
  color: #000;
  font-weight: bold;
}
</style>