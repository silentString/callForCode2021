const routers = [
    {
        path: '/',
        name: 'ApplicationRecord',
        meta: {
            title: '申请记录'
        },
        component: (resolve) => require(['../pages/ApplicationRecord.vue'], resolve)
    },
    {
        path: '/recordList',
        name: 'RecordList',
        meta: {
            title: '申请记录'
        },
        component: (resolve) => require(['../pages/ApplicationRecord.vue'], resolve)
    },
    {
        path: '/addRecord',
        name: 'AddRecord',
        meta: {
            title: '新增申请'
        },
        component: (resolve) => require(['../pages/AddRecord.vue'], resolve)
    },
    {
        path: '/details/:status',
        name: 'Details',
        meta: {
            title: '申请详情'
        },
        component: (resolve) => require(['../pages/Details.vue'], resolve)
    },
    {
        path: '/submissionFailed',
        name: 'SubmissionFailed',
        meta: {
            title: '提交失败'
        },
        component: (resolve) => require(['../pages/SubmissionFailed.vue'], resolve)
    },
    {
        path: '/submittedSuccess',
        name: 'SubmittedSuccess',
        meta: {
            title: '提交成功'
        },
        component: (resolve) => require(['../pages/SubmittedSuccess.vue'], resolve)
    }
]

export default routers;