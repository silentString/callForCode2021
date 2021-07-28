<template>
  <div class="list-content">
    <common-nav
      :title="navTitle"
      :fixed="navFixed"
      :border="navBorder"
      @onClickLeft="onClickLeft"
    />
    <div class="details-content">
      <div class="detail-status">
        <div class="status-item">
          <van-icon
            v-if="details.status === 2 || details.status === 0"
            name="passed"
          />
          <van-icon
            name="close"
            v-if="details.status === 1 || details.status === 3"
          />
          <p>{{ details.status | changeStatus }}</p>
        </div>
        <p v-if="details.status === 3">{{$t('lang.ReasonFailure')}}</p>
      </div>
      <div class="details-item">
        <div class="item-header-name">{{ details.title }}</div>
        <div class="item-header-tip">{{ details.detail }}</div>
        <div class="item-money">
          <span>{{$t('lang.ApplicationAmount')}}</span>
          <span>${{ details.amount | number_format }}</span>
        </div>
        <div class="item-time">
          <span>{{$t('lang.applicationTime')}}</span>
          <span>{{ details.submit_time | ChangeTime }}</span>
        </div>
      </div>
      <div class="details-account">
        <div class="item-account">
          <span>{{$t('lang.ApplyAccount')}}</span>
          <span>
            <span v-if="details.apply_account_type === 1">{{$t('lang.LoanAccount')}}</span>
            <span v-else>{{$t('lang.DonationAccount')}}</span>
            {{ details.apply_account_no | StringHidden(4, 4) }}
          </span>
        </div>
        <div class="item-account">
          <span>{{$t('lang.ReceivingAccount')}}</span>
          <span>{{ details.receipt_account_no | StringHidden(2, 2) }}</span>
        </div>
        <div class="item-account">
          <span>{{$t('lang.Beneficiary')}}</span>
          <span class="payee-accout">{{ details.payee }}</span>
        </div>
        <div class="item-material">
          <div class="material-header">{{$t('lang.ApplicationMaterials')}}</div>
          <div class="material-tip" @click="viewMaterial">
            Water quality improvement contract.pdf
          </div>
        </div>
      </div>
    </div>

    <div
      class="record-content"
      v-if="details.status === '0' || details.status === 0"
    >
      <div class="record-add" @click="RevokeApplication">
        <span>{{$t('lang.Revoke')}}</span>
      </div>
    </div>
    <van-dialog
      v-model="showRevokeDialog"
      confirmButtonColor
      :title="$t('lang.ToCancel')"
      show-cancel-button
      @confirm="confirmRevoke"
    >
    </van-dialog>

    <van-overlay
      :show="showMaterial"
      z-index="999"
      @click.native="handleMaterial"
    >
      <div class="view-wrapper">
        <img src="../assets/shenfen1.jpg" alt="" />
      </div>
    </van-overlay>
    <div class="watermark">
      <img src="../assets/shuiyin.png" alt="" />
    </div>
  </div>
</template>

<script>
// import detailsData from "../../static/mock/details";
import "vant/lib/icon/local.css";
import { WATER_DETAILS, RECALL_APPLICATION } from "../api";
import {
  Icon,
  Button,
  Popup,
  Picker,
  Overlay,
  Field,
  Cascader,
  Dialog,
} from "vant";
import "vant/lib/index.css";
import CommonNav from "../components/common/CommonNav.vue";
export default {
  components: {
    CommonNav,
    [Popup.name]: Popup,
    [Overlay.name]: Overlay,
    [Picker.name]: Picker,
    [Icon.name]: Icon,
    [Field.name]: Field,
    [Dialog.Component.name]: Dialog.Component,
    [Cascader.name]: Cascader,
    [Button.name]: Button,
  },
  //0已提交1已撤回2审批通过3审批拒绝
  data() {
    return {
      navTitle: this.$t('lang.ApplicationDetails'),
      navFixed: true,
      navBorder: false,
      showRevokeDialog: false,
      showMaterial: false,
      account: "1212738e72748823472",
      details: {},
    };
  },
  mounted() {
    this.getDetails();
  },
  watch: {
    $route(to) {
      if (to.params) {
        console.log(to, "tototoot");
      }
    },
  },
  methods: {
    onClickLeft() {
      this.$router.push({ path: "/" });
    },
    RevokeApplication() {
      this.showRevokeDialog = true;
      let params = {
        application_id: this.$route.params.status,
      };
      const formData = new FormData();
      Object.keys(params).forEach((key) => {
        formData.append(key, params[key]);
      });
      RECALL_APPLICATION(formData)
        .then((res) => {
          if (res.data.code === "200") {
            console.log(res);
          } else {
            console.log(3223);
          }
        })
        .catch(() => {
          console.log(1212);
        });
    },
    confirmRevoke() {
      this.showRevokeDialog = false;
    },
    viewMaterial() {
      this.showMaterial = true;
    },
    handleMaterial() {
      this.showMaterial = false;
    },
    getDetails() {
      let params = {
        app_id: this.$route.params.status,
      };
      const formData = new FormData();
      Object.keys(params).forEach((key) => {
        formData.append(key, params[key]);
      });
      WATER_DETAILS(formData)
        .then((res) => {
          if (res.data.code === "200") {
            this.details = res.data.data;
          }
        })
        .catch(() => {
          console.log("error-details");
        });
    },
  },
};
</script>

<style lang="less" scoped>
.list-content {
  width: 100%;
  height: 100vh;
  background-color: #f7f7f7;
  font-family: "PingFang SC";
  box-sizing: content-box;
  background-image: url("../assets/banner-details.png");
  background-repeat: no-repeat;
  background-size: contain;
  position: relative;
  .watermark {
    position: absolute;
    top: 12px;
    right: 18px;
    z-index: 100;
    img {
      width: 100px;
      height: 84px;
    }
  }
}

.details-content {
  margin: 48px auto 0;
  padding: 0 4.3%;
  width: 91.4%;
  .detail-status {
    display: flex;
    justify-content: center;
    flex-flow: column;
    font-family: PingFangSC-Semibold;
    color: #fff;
    height: 94px;
    .status-item {
      display: flex;
      align-items: center;
      font-size: 16px;
      letter-spacing: 0;
      font-weight: 600;
      p {
        margin-left: 6px;
      }
    }
    p {
      font-size: 12px;
      line-height: 0;
    }
  }
  .details-item {
    background: #ffffff;
    border-radius: 8px;
    margin: 0 0 14px 0;
    padding: 16px;
    .item-header-name {
      font-family: PingFangSC-Semibold;
      font-size: 16px;
      color: #2a2a3d;
      letter-spacing: 0;
      line-height: 20px;
      font-weight: 600;
    }
    .item-header-tip {
      font-family: PingFangSC-Regular;
      font-size: 12px;
      color: #62627a;
      letter-spacing: 0;
      line-height: 26px;
      font-weight: 400;
    }
    .item-money {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 44px;
      span {
        display: inline-block;
        font-family: PingFangSC-Regular;
        font-size: 14px;
        letter-spacing: 0;
        font-weight: 400;
        &:first-child {
          color: #62627a;
        }
        &:last-child {
          color: #ff3333;
          font-weight: 600;
        }
      }
    }
    .item-time {
      display: flex;
      justify-content: space-between;
      align-items: center;
      height: 44px;
      span {
        display: inline-block;
        font-family: PingFangSC-Regular;
        font-size: 14px;
        letter-spacing: 0;
        line-height: 14px;
        font-weight: 400;
        &:first-child {
          color: #62627a;
        }
        &:last-child {
          color: #2a2a3d;
        }
      }
    }
  }
  .details-account {
    background: #ffffff;
    border-radius: 8px;
    padding: 16px;
    .item-account {
      display: flex;
      justify-content: space-between;
      line-height: 44px;
      span {
        display: inline-block;
        font-family: PingFangSC-Regular;
        font-size: 14px;
        letter-spacing: 0;
        font-weight: 400;
        &:first-child {
          color: #62627a;
        }
        &:last-child {
          color: #2a2a3d;
          text-align: right;
        }
      }
      .payee-accout{
          text-indent: 2em;
          overflow: hidden;
          white-space: break-spaces;
      }
    }
    .item-material {
      display: flex;
      flex-flow: column;
      .material-header {
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #62627a;
        letter-spacing: 0;
        line-height: 44px;
        font-weight: 400;
      }
      .material-tip {
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #2d70ed;
        letter-spacing: 0;
        line-height: 36px;
        font-weight: 400;
        cursor: pointer;
      }
    }
  }
}

.record-content {
  position: fixed;
  bottom: 0;
  left: 0;
  width: 100%;
  height: 72px;
  background-color: #fff;
  z-index: 100;
  .record-add {
    width: 343px;
    height: 44px;
    margin: 13px auto;
    background-image: linear-gradient(134deg, #77baff 0%, #3590ed 100%);
    border-radius: 4px;
    display: flex;
    justify-content: center;
    align-items: center;
    span {
      font-family: PingFangSC-Medium;
      font-size: 16px;
      color: #fff;
      text-align: center;
      line-height: 16px;
      font-weight: 500;
      margin-left: 4px;
    }
  }
}

.view-wrapper {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
  img {
    width: 311px;
    height: 211px;
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
/deep/ .van-picker__cancel {
  visibility: hidden;
}
</style>