<template>
    <div>
        <div>{{message}}</div>
        <div class="upload-file">
            <input type="file" ref="clearFile" @change="getFile($event)" multiple="multiplt" class="add-file-right-input" accept=".docx,.doc,.pdf,.jpg,.png">
            <br/><span>支持扩展名：.doc .docx .pdf </span>
            <button type="primary" @click="submitAddFile">开始上传</button>
            <button @click="resetAdd">全部删除</button>
        </div>
        <div class="download-file">
            <input v-model="downloadFileName" type="text"/>
            <button @click="download">下载按钮</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
export default {
    name: 'FileUpload',
    data(){
        return {
            message: [],
            addArr: [],
            addType: 'addType',
            addId: 'addType',
            addFileName: 'addType',
            downloadFileName: ''
        }
    },
    mounted() {
        axios.get('http://localhost:8001/list')
        .then(response => {
            this.message = response.data.data
        })
        .catch(error => {console.log(error)})
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
        submitAddFile(){
            // if(0 == this.addArr.length){
            //     this.$message({
            //     type: 'info',
            //     message: '请选择要上传的文件'
            // });
            //     return;
            // }

            var formData = new FormData();
            formData.append('num', this.addType);
            formData.append('linkId',this.addId);
            formData.append('rfilename',this.addFileName);
            for(var i=0; i<this.addArr.length;i++){
                formData.append('file',this.addArr[i]);
            }
            let config = {
                headers: {
                    'Content-Type': 'multipart/form-data',
                    // 'Authorization': this.token
                }
        };
            axios.post('http://localhost:8001/upload', formData, config)
            .then((response) => {
                    var message = response['data']['message'];
                    alert(message)
                    console.log("调用接口", response['data'])
                }
            )
        },
        resetAdd(){
            this.addArr = []
            alert("已经清除完成")
        },
        download(){
            console.log('call download')
            axios
            .get('http://localhost:8001/download/' + this.downloadFileName,{responseType: 'blob'})
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
    }


}
</script>

<style>

</style>