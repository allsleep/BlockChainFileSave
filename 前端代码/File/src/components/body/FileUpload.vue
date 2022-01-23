<template>
<div>
    <h1 style="margin-left: 42%; margin-top:8%">上传文件上链</h1>
    <div class="fileBase">
        <!-- <div>{{message}}</div> -->
        <div class="upload-file">
            <table id="file-box">
                <input type="text" disabled="disabled" value="选择文件" v-show="addArr.length == 0">
                <input v-for="(item, i) in addArr" type="text" :key="i" disabled="disabled" v-model="item.name" />
                <div class="file">选择文件
                    <input type="file" ref="clearFile" @change="getFile($event)" multiple="multiplt" accept=".docx,.doc,.pdf,.jpg,.png">
                </div><br/>
            </table>
            <span style="color: lightgray; margin-left: 2%">支持扩展名：.doc .docx .pdf .png</span><br />
            <div>
                <br/> <label>选择是否上链:</label><br/><br/>
                <label style="margin-left: 8%;">上链<input type="radio" name="blockchain" @change="putchain"/></label>
                <label style="margin-left: 5%;">不上链<input type="radio" name="blockchain" @change="notchain" checked="true"/></label>
            </div><br>
            <button type="primary" class="click_button" @click="submitAddFile" style="margin-left: 0px;">开始上传</button>
            <button @click="resetAdd" class="click_button">全部删除</button>
            <button @click="download" class="click_button">下载文件</button>
        </div><br/>
    </div>
</div>
</template>

<script>
import Layout from '../Layout.vue';
export default {
    components: { Layout },
    name: 'FileUpload',
    data(){
        return {
            fileList: [],
            addArr: [],
            addType: 'addType',
            addId: 'addType',
            addFileName: 'addType',
            downloadFileName: '',
            ischain: false
        }
    },
    mounted() {
        // this.$axios.get('/file/api/bucketList',
        // {params:{}}
        // )
        // .then(res => {
        //     if (res.data.code === "2000"){

        //     }else{
        //         console.log(res)
        //         this.fileList = res.data.data
        //     }
        // })
        // .catch(error => {console.log(error)})
    },
    methods: {
        getFile(event){
            var file = event.target.files;
            for(var i = 0; i<file.length;i++){
            //    上传类型判断
                var imgName = file[i].name;
                var idx = imgName.lastIndexOf(".");  
                if (idx != -1){
                    var ext = imgName.substr(idx+1).toUpperCase();   
                    ext = ext.toLowerCase( ); 
                    if (ext!='pdf' && ext!='doc' && ext!='docx' && ext!='jpg' && ext!='png'){
                        alert("文件类型错误")
                    }else{
                        this.addArr.push(file[i]);
                        console.log(this.addArr)
                    }   
                }else{
                    alert("文件类型错误")
                }
            }
        },
        async submitAddFile(){
            if(0 == this.addArr.length){
                alert("请选择文件")
                return
            }
            var formData = new FormData();
            formData.append('num', this.addType);
            formData.append('linkId',this.addId);
            formData.append('rfilename',this.addFileName);
            formData.append('accountId', sessionStorage.getItem('accountId'))
            for(var i=0; i<this.addArr.length;i++){
                formData.append('file',this.addArr[i]);
            }
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    'token': sessionStorage.getItem('token')
                }
            };

            await this.$axios.post('/file/api/upload', formData, config)
            .then((res) => {
                    var message = res.data.message;
                    alert(message)
                    console.log("调用接口", res.data)
                    if (this.ischain) {
                        let fileId = ''
                        let fileMD5 = ''
                        for (var i = 0; i < this.addArr.length; i++){
                            fileId = res.data.data[0].fileId;
                            fileMD5 = res.data.data[0].fileMD5;
                            this.executeBlockChain(fileId, fileMD5);
                            this.$router.push('/putfileresult')
                        }
                    }
                }
            ).catch((err) => {console.log(err);return})
        },
        resetAdd(){
            this.addArr = []
            alert("已经清除完成")
        },
        download(){
            console.log('call download')
            this.$axios
            .get('file/api/download/' + this.downloadFileName,{responseType: 'blob'})
            .then((res) => {
                console.log(res.data)
                let blob = new Blob([res.data], {
                type: "image/png",
                }); // 2.获取请求返回的response对象中的blob 设置文件类型，这里以excel为例
                let url = window.URL.createObjectURL(blob); // 3.创建一个临时的url指向blob对象
                // 4.创建url之后可以模拟对此文件对象的一系列操作，例如：预览、下载
                let a = document.createElement("a");
                a.href = url;
                a.download = "下载文件.png";
                a.click();
                // 5.释放这个临时的对象url
                window.URL.revokeObjectURL(url);
            })
            .catch((error) => {
                console.log(error);
            })
        },
        putchain() {
            this.ischain = true;
        },
        notchain() {
            this.ischain = false;
        },
        executeBlockChain(fileId, fileMD5){
            console.log("上链")
            let data = {
                fileId: fileId,
                fileMD5: fileMD5
            }
            let config = {
                headers: {
                    // 'Content-Type': ' application/json',
                    'token': sessionStorage.getItem('token')
                }
            }
            this.$axios.post("http://localhost:40001/blockchain/api/upload", data, config)
            .then(res => {
                console.log(res)
                sessionStorage.setItem('blockchainResult', JSON.stringify(res.data.data))
            }).catch((error) => console.log(error))
        }
    }


}
</script>

<style>
.fileBase {
    margin: auto;
    margin-left: 40%;
    margin-top: 8%;
    /* text-align: center; */ 
}

#file-box{
    height: 25px;
    line-height: 15px;
    vertical-align: middle;
}

#file-box > input {
    width: 200px;
}

.file {
    position: relative;
    display: inline-block;
    background:linear-gradient(to right,#beb7bb 0%,#131101e1 100%);
    border: 0;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: white;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin:0px 0px 0px 20px;
    vertical-align: middle;
}
.file > input {
    position: absolute;
    font-size: 100px;
    right: 0;
    top: 0;
    opacity: 0;
}
.file:hover {
    background:linear-gradient(to right,#f3097e 0%,#e6d813e1 100%);    border-color: white;
    color: #004974;
    text-decoration: none;
}

.click_button {
    position: relative;
    display: inline-block;
    background:linear-gradient(to right,#beb7bb 0%,#131101e1 100%);
    border: 0;
    border-radius: 4px;
    padding: 4px 12px;
    overflow: hidden;
    color: white;
    text-decoration: none;
    text-indent: 0;
    line-height: 20px;
    margin:15px 10px 0px 15px;
}

.click_button:hover {
    background:linear-gradient(to right,#f3097e 0%,#e6d813e1 100%);
}
</style>