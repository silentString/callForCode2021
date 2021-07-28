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
        <img src="../assets/submit-success.png" alt="" />
        <div class="fail-title">{{$t('lang.SubmittedSuccessfully')}}</div>
        <div class="fail-tip">{{ successList.detail }}</div>
      </div>
      <div class="fail-middle">
        <div class="fail-item">
          <span class="item-label">{{$t('lang.ApplicationAmount')}}</span>
          <span class="item-money"
            >${{ successList.amount | number_format }}</span
          >
        </div>
        <div class="fail-item">
          <span class="item-label">{{$t('lang.ApplyAccount')}}</span>
          <span class="item-des">
            <span v-if="successList.apply_account_type === 1">{{$t('lang.LoanAccount')}}</span>
            <span v-else>{{$t('lang.DonationAccount')}}</span>
            {{ successList.apply_account_no | StringHidden(4, 4) }}</span
          >
        </div>
        <div class="fail-item">
          <span class="item-label">{{$t('lang.ReceivingAccount')}}</span>
          <span class="item-des">
            {{ successList.receipt_account_no | StringHidden(4, 4) }}</span
          >
        </div>
      </div>
      <div class="fail-footer">
        <div class="fail-btn" @click="goToHome">
          <span>{{$t('lang.BackHomepage')}}</span>
        </div>
        <div class="restart" @click="goToRestart">
          <span>{{$t('lang.seeDetails')}}</span>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import CommonNav from "../components/common/CommonNav.vue";
import { Loading, Overlay } from "vant";
export default {
  components: {
    CommonNav,
    [Overlay.name]: Overlay,
    [Loading.name]: Loading,
  },
  data() {
    return {
      navTitle: "",
      navFixed: true,
      navBorder: false,
      successList: {},
    };
  },
  mounted() {
    let appList = window.localStorage.getItem("appLists");
    if (appList && appList.length > 0) {
      this.successList = JSON.parse(appList);
    }
  },
  methods: {
    onClickLeft() {
      this.$router.push("/addRecord");
    },
    goToHome() {
      this.$router.push("/");
    },
    goToRestart() {
      this.$router.push(`/details/${this.$route.query.id}`);
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
  .fail-middle {
    margin: 0 20px 40px;
    .fail-item {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 44px;
      line-height: 44px;
      border-bottom: 1px solid #f7f7f7;
      &:last-child {
        border-bottom: none;
      }
      .item-label {
        color: #62627a;
      }
      .item-money {
        color: #ff3333;
      }
      .item-des {
        color: #2a2a3d;
        text-align: right;
      }
      span {
        letter-spacing: 0;
        line-height: 14px;
        font-weight: 400;
        font-family: PingFangSC-Regular;
        font-size: 14px;
        display: inline-block;
      }
    }
  }
  .fail-footer {
    display: flex;
    justify-content: space-around;
    align-self: center;
    .fail-btn {
      width: 156px;
      height: 44px;
      line-height: 36px;
      text-align: center;
      border: 1px solid #2d70ed;
      border-radius: 4px;
      cursor: pointer;
      span {
        font-family: PingFangSC-Medium;
        font-size: 16px;
        color: #2d70ed;
        text-align: center;
        line-height: 16px;
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
        line-height: 16px;
        font-weight: 700;
      }
    }
  }
}
.loading-content {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
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