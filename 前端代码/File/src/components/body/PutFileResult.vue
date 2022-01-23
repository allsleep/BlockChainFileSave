<template>
<div class="res_base">
    <h1>上传文件结果</h1>
    <div class="res_item">
        <div>当前区块数：<span>{{blockNumber}}</span></div>
        <div style="margin-top: 5%;">交易哈希值：<span>{{transactionHash}}</span></div>
        <!-- <div>调用合约地址：<span>{{}}</span></div> -->
    </div>
</div>
</template>

<script>
export default {
    name: 'PutFileResult',
    data(){
        return {
            blockNumber: null,
            transactionHash: null
        }
    },
    mounted() {
        let res = JSON.parse(sessionStorage.getItem('blockchainResult'))
        this.blockNumber = res.blockNumber
        this.transactionHash = res.transactionHash
    }
}
</script>

<style>
.res_base {
    margin: auto;
    margin-left: 30%;
    margin-top: 8%;
}

.res_item {
    margin-top: 8%;
}
</style>