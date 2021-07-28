<template>
  <div class="list-content">
    <common-nav
      :title="navTitle"
      :fixed="navFixed"
      :border="navBorder"
      @onClickLeft="onClickLeft"
    />
    <div class="header-record">
      <div class="header-title">{{ watersData.title }}</div>
      <div class="header-tip">
        <div class="tip-label">
          <span>{{ $t("lang.TotalProjectFunds") }} </span
          ><span>${{ watersData.total_amount | number_format(2) }}</span>
        </div>
        <div class="tip-label">
          <span>{{ $t("lang.AvailableFunds") }}</span
          ><span>${{ watersData.ava_amount | number_format(2) }}</span>
        </div>
      </div>
    </div>
    <div class="add-list">
      <div class="select-money">
        <div class="accout-item">
          <van-field
            :label="$t('lang.UseOfFunds')"
            :value="apply_title"
            :border="false"
            :right-icon="applyIcon"
            :placeholder="$t('lang.Pleaseusefunds')"
            @click="handleApplce"
          />
          <van-popup v-model="showApplyPicker" round position="bottom">
            <van-cascader
              v-model="cascaderValue"
              :title="$t('lang.UseOfFunds')"
              :options="applyColumns"
              active-color="#2D70ED "
              @close="showApplyPicker = false"
              @finish="onConfirmApply"
            />
          </van-popup>
        </div>
        <div class="accout-item-tip">
          <van-field
            v-model="applyTip"
            :border="false"
            :placeholder="$t('lang.PleaseEnterDetails')"
          />
        </div>
        <div class="accout-item">
          <van-field
            :label="$t('lang.ApplicationAmount')"
            v-model="amount"
            type="digit"
            :border="false"
            :placeholder="$t('lang.PleaseApplicationAmount')"
          />
          <span class="tip-money">$</span>
        </div>
      </div>
      <div class="account-info">
        <div class="accout-item">
          <van-field
            :label="$t('lang.ApplyAccount')"
            :value="apply_account_no"
            :border="false"
            :right-icon="moneyIcon"
            :placeholder="$t('lang.PleaseChooseAccount')"
            @click="handleMoney"
          />
          <van-action-sheet
            v-model="showMoneyPicker"
            @select="selectAccunt"
            :actions="accountColumns"
            :title="$t('lang.ApplyAccount')"
          >
          </van-action-sheet>
        </div>
        <div class="accout-item">
          <van-field
            :label="$t('lang.ReceivingAccount')"
            type="digit"
            v-model="receipt_account_no"
            :border="false"
            :placeholder="$t('lang.PleaseEnterReceiving')"
          />
        </div>
        <div class="accout-item">
          <van-field
            :label="$t('lang.Beneficiary')"
            v-model="payee"
            :border="false"
            :placeholder="$t('lang.PleaseEnterpPayee')"
          />
        </div>
      </div>
      <div class="add-material">
        <div class="upload-header">
          <span>{{ $t("lang.AddMaterial") }}</span>
          <span>{{ $t("lang.MoreMaterial") }}</span>
        </div>
        <div class="upload-list" v-for="(item, index) in fileList" :key="index">
          <span @click="viewMater">{{ item.name }}</span>
          <span @click="deleteMateral(index)"><van-icon name="cross" /></span>
        </div>
        <div class="upload-add">
          <van-uploader
            accept=".doc,.docx,.pdf,application/msword,application/vnd.openxmlformats-officedocument.wordprocessingml.document"
            :after-read="afterRead"
          >
            <div class="record-add-content">
              <div class="record-add">
                <van-icon name="add-o" size="18" color="#2D70ED" />
                <span>{{ $t("lang.AddMaterial") }}</span>
              </div>
            </div>
          </van-uploader>
        </div>
      </div>
    </div>

    <van-overlay
      :show="showMaterial"
      z-index="999"
      @click.native="handleMaterial"
    >
      <div class="view-wrapper">
        <img src="../assets/no-list.png" alt="" />
      </div>
    </van-overlay>
    <van-dialog
      v-model="showLoading"
      title=""
      show-cancel-button
      :showConfirmButton="false"
      :showCancelButton="false"
      @confirm="handleLoading"
    >
      <div class="swiper-load-content">
        <div class="swiper-list">
          <van-swipe
            ref="swipe"
            :height="60"
            :width="350"
            style="width: 350px; height: 60px; text-align: center"
            :loop="true"
            :autoplay="2400"
            vertical
            :show-indicators="false"
            indicator-color="white"
            @change="changeSwiper"
          >
            <van-swipe-item>Getting transaction information....</van-swipe-item>
            <van-swipe-item>Checking the use of funds...</van-swipe-item>
            <van-swipe-item
              >Checking the authenticity of the information of the
              counterparties....</van-swipe-item
            >
          </van-swipe>
        </div>
        <p>Submitting for approve...</p>
      </div>
    </van-dialog>

    <div class="record-content">
      <div class="record-add" @click="submitApplication">
        <span>{{ $t("lang.submitApplication") }}</span>
      </div>
    </div>
  </div>
</template>

<script>
import {
  ADD_APPLICATION,
  SEARCH_ACCOUNT,
  GET_APPLICATION_ITEM,
  SEARCH_ITEM_INFO,
  WATER_APPROVE,
} from "../api";
import "vant/lib/icon/local.css";
import {
  Icon,
  Button,
  Popup,
  Picker,
  Field,
  Loading,
  Cascader,
  ActionSheet,
  Uploader,
  Overlay,
  Swipe,
  SwipeItem,
  Dialog,
} from "vant";
import "vant/lib/index.css";
import CommonNav from "../components/common/CommonNav.vue";
export default {
  name: "application-record",
  components: {
    CommonNav,
    [Popup.name]: Popup,
    [Picker.name]: Picker,
    [Overlay.name]: Overlay,
    [Icon.name]: Icon,
    [Field.name]: Field,
    [ActionSheet.name]: ActionSheet,
    [Uploader.name]: Uploader,
    [Cascader.name]: Cascader,
    [Loading.name]: Loading,
    [Button.name]: Button,
    [Swipe.name]: Swipe,
    [SwipeItem.name]: SwipeItem,
    [Dialog.Component.name]: Dialog.Component,
  },
  data() {
    return {
      navTitle: this.$t("lang.NewApplication"),
      navFixed: true,
      navBorder: false,
      moneyValue: "",
      showMoneyPicker: false,
      showApplyPicker: false,
      showMaterial: false,
      showLoading: false,
      cascaderValue: "",
      apply_title: "",
      apply_account_type: "",
      apply_account_no: "",
      receipt_account_no: "",
      applyTip: "",
      payee: "",
      amount: "",
      moneyIcon: "arrow",
      applyIcon: "arrow",
      fileList: [],
      watersData: {},
      columns: [],
      applyColumns: [],
      accountColumns: [],
      successList:[]
    };
  },
  mounted() {
    this.getInit();
  },
  methods: {
    changeSwiper(index){
      if(index===2||index==='2'){
        setTimeout(()=>{
          this.showLoading = false;
           this.$router.push(`/submittedSuccess?id=${this.successList.id}`);
        },2000) 
      }
    },
    handleLoading() {
      this.showLoading = true;
    },
    handleApplce() {
      this.showApplyPicker = true;
      this.moneyIcon = "arrow-down";
    },
    onConfirmApply({ selectedOptions }) {
      this.apply_title = selectedOptions.map((option) => option.text).join("/");
      this.applyIcon = "arrow";
      this.showApplyPicker = false;
    },
    getInit() {
      let params = {
        item_id: 1,
      };
      const formData = new FormData();
      Object.keys(params).forEach((key) => {
        formData.append(key, params[key]);
      });
      SEARCH_ITEM_INFO(formData)
        .then((res) => {
          if (res.data.code === "200") {
            this.watersData = res.data.data;
          } else {
            this.watersData = {};
          }
        })
        .catch(() => {
          this.watersData = {
            title: "Jinqiao village water quality improvement project",
            status: "0",
            total_amount: "372649384957834",
            ava_amount: "327324473284738",
            id: 1,
          };
        });
      SEARCH_ACCOUNT(formData)
        .then((res) => {
          if (res.data.code === "200") {
            const data = res.data.data;
            let item = {
              text: "",
              value: "",
            };
            item.text = data.donate;
            item.subname = `Balance $${data.donate}`;
            item.name = `Loan account${this.StrHidden(data.donate_acc, 4, 4)}`;
            item.value = data.donate_acc;
            item.type = 0;
            this.accountColumns.push(item);
            let list = {
              text: "",
              value: "",
            };
            list.text = data.loan;
            list.name = `Donation account${this.StrHidden(
              data.loan_acc,
              4,
              4
            )}`;
            list.subname = `Balance $${data.loan}`;
            list.value = data.loan_acc;
            list.type = 1;
            this.accountColumns.push(list);
          } else {
            this.accountColumns = [];
          }        
        })
        .catch(() => {
          console.log("获取列表信息错误");
        });
      GET_APPLICATION_ITEM()
        .then((res) => {
          if (res.data.code === "200") {
            const data = res.data.data;
            data.map((item) => {
              let data = {
                text: "",
                value: "",
              };
              data.text = item;
              data.value = item;
              this.applyColumns.push(data);
            });
          } else {
            this.applyColumns = [];
          }
        })
        .catch(() => {
          console.log("获取列表信息失败");
        });
    },

    StrHidden(str, frontLen, endLen) {
      //str：要进行隐藏的变量  frontLen: 前面需要保留几位    endLen: 后面需要保留几位
      var len = str.length - frontLen - endLen;
      var xing = "";
      for (var i = 0; i < len; i++) {
        xing += "*";
      }
      return (
        str.substring(0, frontLen) + xing + str.substring(str.length - endLen)
      );
    },
    handleMoney() {
      this.showMoneyPicker = true;
      this.moneyIcon = "arrow-down";
    },
    onClickLeft() {
      this.$router.push("/");
    },
    selectAccunt(action) {
      this.apply_account_no = action.value;
      this.apply_account_type = action.type;
      this.moneyIcon = "arrow";
      this.showMoneyPicker = false;
    },
    submitApplication() {
      let params = {
        title: this.apply_title,
        amount: this.amount,
        detail: this.applyTip,
        apply_account_type: this.apply_account_type,
        apply_account_no: this.apply_account_no,
        receipt_account_no: this.receipt_account_no,
        payee: this.payee,
      };
      const formData = new FormData();
      Object.keys(params).forEach((key) => {
        formData.append(key, params[key]);
      });
      ADD_APPLICATION(formData)
        .then((res) => {
          if (res.data.code === "200") {
            this.handleApprove(res.data.data);
          }
        })
        .catch(() => {
          console.log("提交信息失败");
        });
    },
    handleApprove(app_id) {
      window.localStorage.setItem("appLists", "");
      this.successList=[];
      let params = {
        application_id: app_id,
        application_title: this.apply_title,
        acc_type: this.apply_account_type,
        amount: this.amount,
      };
      const formData = new FormData();
      Object.keys(params).forEach((key) => {
        formData.append(key, params[key]);
      });
      WATER_APPROVE(formData)
        .then((res) => {
          if (res.data.code === "200") {
            const data = res.data.data;
            this.successList = data;
            this.showLoading = true;
            window.localStorage.setItem("appLists", JSON.stringify(data));
          } else {
            this.$router.push({ path: "/submissionFailed" });
          }
        })
        .catch(() => {
          this.$router.push({ path: "/submissionFailed" });
        });
    },
    deleteMateral(idx) {
      for (let i = 0; i < this.fileList.length; i++) {
        if (i === idx) {
          this.fileList.splice(i, 1);
        }
      }
    },
    viewMater() {
      this.showMaterial = true;
    },
    handleMaterial() {
      this.showMaterial = false;
    },
    afterRead(file) {
      let list = {
        name: file.file.name,
      };
      this.fileList.push(list);
    },
  },
};
</script>
<style lang="less" scoped>
.list-content {
  width: 100%;
  height: 100vh;
  font-family: "PingFang SC";
  background-color: #f7f7f7;
  background-image: url("../assets/banner-index.png");
  background-repeat: no-repeat;
  background-size: contain;
  .header-record {
    margin: 48px 4.3% 0;
    width: 91.4%;
    .header-title {
      font-family: PingFangSC-Medium;
      font-size: 16px;
      color: #ffffff;
      letter-spacing: 0;
      line-height: 58px;
      font-weight: 600;
      overflow: hidden;
      word-break: break-all;
      white-space: nowrap;
    }
    .header-tip {
      color: #ffffff;
      font-size: 12px;
      display: flex;
      justify-content: space-between;
      .tip-label {
        span {
          display: block;
          line-height: 20px;
        }
      }
    }
  }
  .add-list {
    margin-bottom: 50px;
    .select-money {
      margin: 22px auto 12px;
      width: 91.4%;
      background: #ffffff;
      border-radius: 8px;
      overflow: hidden;
      .accout-item {
        height: 52px;
        position: relative;
        &:first-child {
          margin-top: 8px;
        }
        .tip-money {
          display: inline-block;
          position: absolute;
          top: 12px;
          right: 22px;
          font-family: PingFangSC-Regular;
          font-size: 14px;
          color: #2a2a3d;
          letter-spacing: 0;
          text-align: right;
          font-weight: 400;
        }
      }
      .accout-item-tip {
        height: 52px;
        margin: 0 12px;
        /deep/.van-cell {
          background-color: #f7f7f7;
        }
      }
    }
    .account-info {
      background: #ffffff;
      border-radius: 8px;
      width: 91.4%;
      margin: 0 auto;
      overflow: hidden;
      .accout-item {
        height: 52px;
        &:first-child {
          margin-top: 8px;
        }
      }
    }
    .add-material {
      background: #ffffff;
      border-radius: 8px;
      margin: 10px auto;
      width: 91.4%;
      .upload-header {
        padding: 0 4.3%;
        span {
          line-height: 48px;
          &:first-child {
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #2a2a3d;
            letter-spacing: 0;
            font-weight: 400;
            margin-right: 3px;
          }
          &:last-child {
            font-family: PingFangSC-Regular;
            font-size: 12px;
            color: #62627a;
            letter-spacing: 0;
            font-weight: 400;
          }
        }
      }
      .upload-list {
        display: flex;
        justify-content: space-between;
        font-family: PingFangSC-Regular;
        font-size: 14px;
        color: #2d70ed;
        letter-spacing: 0;
        line-height: 24px;
        font-weight: 400;
        padding: 0 4.3%;
        span {
          display: inline-block;
        }
      }
    }
  }
  .upload-add {
    margin: 0 auto;
    display: flex;
    justify-content: center;
    .record-add-content {
      width: 100%;
      background-color: #fff;
      z-index: 1000;
      .record-add {
        width: 311px;
        height: 44px;
        margin: 13px auto;
        background: #f7f7f7;
        border-radius: 4px;
        display: flex;
        justify-content: center;
        align-items: center;
        cursor: pointer;
        span {
          font-family: PingFangSC-Medium;
          font-size: 16px;
          color: #2d70ed;
          text-align: center;
          line-height: 16px;
          font-weight: 500;
          margin-left: 4px;
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
      cursor: pointer;
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
}
.van-dialog {
  background-color: transparent;
}
.swiper-load-content {
  display: flex;
  align-items: center;
  justify-content: center;
  flex-flow: column;
  height: 100%;
  font-size: 18px;
  color: #fff;
  .swiper-list {
    text-align: center;
    width: 350px;
    height: 70px;
  }
}
.notice-swipe {
  height: 40px;
  line-height: 40px;
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
.loading-content {
  display: flex;
  align-items: center;
  justify-content: center;
  height: 100%;
}
.account-content-list {
  min-height: 100px;
  .account-list {
    padding: 4.3%;
    height: 68px;
    display: flex;
    justify-content: center;
    flex-flow: column;
    .list-loc-top {
      font-family: PingFangSC-Regular;
      font-size: 14px;
      color: #2a2a3d;
      line-height: 36px;
      font-weight: 400;
    }
    .list-loc-bottom {
      font-family: PingFangSC-Regular;
      font-size: 12px;
      color: #62627a;
      line-height: 12px;
      font-weight: 400;
    }
  }
}
/deep/ .van-action-sheet__header {
  font-weight: 600;
  font-size: 18px;
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
/deep/.van-field__label {
  font-size: 14px;
  color: #2a2a3d;
}
</style>