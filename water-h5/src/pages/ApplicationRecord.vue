<template>
  <div class="list-content">
    <common-nav
      :title="navTitle"
      :fixed="navFixed"
      :border="navBorder"
      @onClickLeft="onClickLeft"
    />
    <div class="header-record">
      <div class="header-title">
        {{ watersData.title }}
        <img src="../assets/xiala.png" alt="" />
      </div>
      <div class="header-tip">
        <div class="tip-label">
          <span>{{$t('lang.TotalProjectFunds')}}</span
          ><span>${{ watersData.total_amount | number_format(2) }}</span>
        </div>
        <div class="tip-label">
          <span>{{$t('lang.AvailableFunds')}}</span
          ><span>${{ watersData.ava_amount | number_format(2) }}</span>
        </div>
      </div>
    </div>
    <div class="normal-tip" v-if="watersData.status === 'normal'">
      <van-icon name="passed" size="14" color="#14a684" />
      <span>{{$t('lang.normalTip')}}</span>
    </div>
    <div class="error-tip" v-else-if="watersData.status === 'abnormal'">
      <van-icon name="info-o" size="14" color="#FF3333" />
      <span>{{$t('lang.errorTip')}} </span>
    </div>
    <div class="list-record">
      <div class="record-list" v-if="watersData.status === 'normal'">
        <div
          class="item-list"
          v-for="(item, index) in recordLists"
          :key="index"
          @click="gotoDetails(item.application_id)"
        >
          <div class="item-top">
            <div class="name">{{ item.title }}</div>
            <div :class="item.status | changeClassName">
              {{ item.status | changeStatus }}
            </div>
          </div>
          <div class="item-middle">
            <div class="time">{{ item.date | ChangeTime }}</div>
            <div class="moneny">${{ item.amount | number_format(2) }}</div>
          </div>
          <div class="item-bottom" v-if="item.status === '0'">
            <div class="item-cancel">{{$t('lang.Revoke')}} </div>
          </div>
        </div>
      </div>
      <div class="record-list-no" v-if="recordLists.length === 0">
        <img src="../assets/no-list.png" alt="" />
      </div>
    </div>
    <div class="record-content">
      <div class="record-add" @click="goToAdd">
        <van-icon name="add-o" size="18" color="#2D70ED" />
        <span>{{$t('lang.NewApplication')}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import "vant/lib/icon/local.css";
import { Icon, Button } from "vant";
import "vant/lib/index.css";
import CommonNav from "../components/common/CommonNav.vue";
import datas from "../../static/mock/recordList";

import { SEARCH_ITEM_INFO, SEARCH_ITEM_APPLICATION } from "../api";
export default {
  name: "application-record",
  components: {
    CommonNav,
    [Icon.name]: Icon,
    [Button.name]: Button,
  },
  data() {
    return {
      navTitle: this.$t('lang.ApplicationRecord'),
      navFixed: true,
      navBorder: false,
      recordLists: [],
      watersData: {},
    };
  },
  mounted() {
    this.getData();
  },
  methods: {
    onClickLeft() {
      console.log(11212);
    },
    goToAdd() {
      this.$router.push({ path: "/addRecord" });
    },
    gotoDetails(status) {
      this.$router.push(`/details/${status}`);
    },
    getData() {
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
            status: "normal",
            total_amount: "372649384957834",
            ava_amount: "327324473284738",
            id: 1,
          };
          console.log("error---首页");
        });
      SEARCH_ITEM_APPLICATION(formData)
        .then((res) => {
          if (res.data.code === "200") {
            this.recordLists = res.data.data;
          } else {
            this.recordLists = [];
          }
        })
        .catch(() => {
          this.recordLists = datas.record;
          console.log("error---首页-list");
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
  background-image: url("../assets/banner-index.png");
  background-repeat: no-repeat;
  background-size: contain;
  .header-record {
    margin: 48px auto 0;
    padding: 0 4.3%;
    width: 91.4%;
    .header-title {
      font-family: PingFangSC-Medium;
      font-size: 16px;
      color: #ffffff;
      letter-spacing: 0;
      line-height: 58px;
      font-weight: 600;
      display: flex;
      align-items: center;
      white-space: nowrap;
      overflow: hidden;
      img {
        width: 20px;
        height: 20px;
        margin-left: 3px;
      }
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
  .error-tip {
    margin: 2.1% 4.3% 1.2%;
    width: 91.4%;
    height: 32px;
    font-family: PingFangSC-Regular;
    font-size: 12px;
    letter-spacing: 0;
    font-weight: 400;
    background: #fff;
    border-radius: 2px;
    display: flex;
    align-items: center;
    span {
      color: #ff3333;
    }
    i {
      display: inline-block;
      margin: 0 1.6% 0 3.2%;
    }
  }
  .normal-tip {
    margin: 2.1% 4.3% 1.2%;
    width: 91.4%;
    height: 32px;
    font-family: PingFangSC-Regular;
    font-size: 12px;
    letter-spacing: 0;
    font-weight: 400;
    background: #fff;
    border-radius: 8px;
    display: flex;
    align-items: center;
    span {
      color: #14a684;
    }
    i {
      display: inline-block;
      margin: 0 1.6% 0 3.2%;
    }
  }
  .list-record {
    padding: 10px 4.3% 4%;
    width: 91.4%;
    margin-bottom: 80px;
    .list-channel {
      font-family: PingFangSC-Regular;
      font-size: 12px;
      color: #2a2a3d;
      letter-spacing: 0;
      line-height: 40px;
      font-weight: 400;
    }
    .record-list {
      background: #ffffff;
      border-radius: 8px;
      display: flex;
      flex-flow: column;
      .item-list {
        margin: 0 4.3%;
        padding: 6.5% 0;
        border-bottom: 1px solid #ddd;
        &:last-child {
          border-bottom: none;
        }
        .item-top {
          display: flex;
          justify-content: space-between;
          align-items: center;
          .name {
            font-family: PingFangSC-Medium;
            font-size: 16px;
            color: #2a2a3d;
            letter-spacing: 0;
            line-height: 16px;
            font-weight: 600;
          }
          .sub {
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #2d70ed;
            letter-spacing: 0;
            text-align: right;
            line-height: 14px;
            font-weight: 400;
          }
          .fail {
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #ff3333;
            letter-spacing: 0;
            text-align: right;
            line-height: 14px;
            font-weight: 400;
          }
          .pass {
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #2a2a3d;
            letter-spacing: 0;
            text-align: right;
            line-height: 14px;
            font-weight: 400;
          }
          .canceled {
            font-family: PingFangSC-Regular;
            font-size: 14px;
            color: #9d9db3;
            letter-spacing: 0;
            text-align: right;
            line-height: 14px;
            font-weight: 400;
          }
        }
        .item-middle {
          display: flex;
          justify-content: space-between;
          align-items: center;
          height: 36px;
          .time {
            font-family: PingFangSC-Regular;
            font-size: 12px;
            color: #62627a;
            letter-spacing: 0;
            line-height: 12px;
            font-weight: 400;
          }
          .moneny {
            font-family: PingFangSC-Medium;
            font-size: 14px;
            color: #2a2a3d;
            letter-spacing: 0;
            text-align: right;
            line-height: 14px;
            font-weight: 500;
          }
        }
        .item-bottom {
          display: flex;
          justify-content: flex-end;
          .item-cancel {
            width: 44px;
            height: 24px;
            border: 1px solid #2d70ed;
            border-radius: 4px;
            font-family: PingFangSC-Regular;
            font-size: 12px;
            color: #2d70ed;
            letter-spacing: 0;
            text-align: center;
            line-height: 24px;
            font-weight: 400;
          }
        }
      }
    }
    .record-list-no {
      display: flex;
      justify-content: center;
      align-items: center;
      height: 60vh;
      img {
        width: 160px;
        height: 104px;
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
    z-index: 1000;
    .record-add {
      width: 343px;
      height: 44px;
      margin: 13px auto;
      border: 1px solid #2d70ed;
      border-radius: 4px;
      display: flex;
      justify-content: center;
      align-items: center;
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