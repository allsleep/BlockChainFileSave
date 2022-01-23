package com.fisco.client;

import com.fisco.common.CommonClient;
import com.fisco.contract.TestKV;
import com.fisco.utils.SpringUtils;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.BcosSDK;
import org.fisco.bcos.sdk.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.model.TransactionReceipt;
import org.fisco.bcos.sdk.transaction.model.exception.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.util.Properties;

/**
 * @Classname KVClient
 * @Description sdk客户端，可以不使用ApplicationRunner，这里是让spring容器启动部署合约
 */
@Service
@Slf4j
public class KVClient extends CommonClient implements ApplicationRunner {

//    public static final Logger logger = LoggerFactory.getLogger(KVClient.class.getName());
    @Autowired
    public BcosSDK sdk;

    public TransactionReceipt set(String fileID, String fileMD5) {

        TestKV testKV = (TestKV) getContractMap().get("TestKV");
        TransactionReceipt receipt = testKV.set(fileID, fileMD5);
        log.info("KVClient");
        log.info("结果：{}", receipt);
        return receipt;
    }

    public Tuple2<Boolean, String> get(String fileId) throws ContractException {
        TestKV testKV = (TestKV) getContractMap().get("TestKV");
        Tuple2<Boolean, String> getValue = testKV.get(fileId);
        log.info("KVClient");
        log.info("结果：{}", getValue);
        return getValue;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String address = null;
        try {
            address = loadAssetAddr();
            getContractMap().put("TestKV", TestKV.load(address, sdk.getClient(), sdk.getClient().getCryptoSuite().getCryptoKeyPair()));
        }catch (Exception e){
            //第一次部署，并记录合约地址
            TestKV testKV = TestKV.deploy(sdk.getClient(), sdk.getClient().getCryptoSuite().getCryptoKeyPair());
            getContractMap().put("TestKV", testKV);
            log.info("合约地址:  " + testKV.getContractAddress());
            recordAssetAddr(testKV.getContractAddress());
        }
    }

    public String loadAssetAddr() throws Exception {
        // load Asset contact address from contract.properties
        Properties prop = new Properties();
        final Resource contractResource = new ClassPathResource("contract.properties");
        prop.load(contractResource.getInputStream());

        String contractAddress = prop.getProperty("address");
        if (contractAddress == null || contractAddress.trim().equals("")) {
            throw new Exception(" load Asset contract address failed, please deploy it first. ");
        }
        log.info(" load Asset address from contract.properties, address is {}", contractAddress);
        return contractAddress;
    }

    public void recordAssetAddr(String address) throws Exception {
        Properties prop = new Properties();
        prop.setProperty("address", address);
        final Resource contractResource = new ClassPathResource("contract.properties");
        FileOutputStream fileOutputStream = new FileOutputStream(contractResource.getFile());
        prop.store(fileOutputStream, "contract address");
        fileOutputStream.flush();
        fileOutputStream.close();
    }
}
