pragma solidity >=0.4.24 <0.6.11;

import "./Table.sol";

contract upload {
    // event
    event RegisterEvent(int256 ret, string indexed fileId, string indexed fileMD5);
    
    constructor() public {
        createTable();
    }

    function createTable() private {
        TableFactory tf = TableFactory(0x1001); 
        tf.createTable("t_file_block", "file_id", "file_md5");
    }

    function openTable() internal returns(Table) {
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_file_block");
        return table;
    }


    function select(string memory fileId) public view returns(int256, string) {
        // 打开表
        TableFactory tf = TableFactory(0x1001);
        Table table = tf.openTable("t_file_block");
        // 查询
        Entries entries = table.select(fileId, table.newCondition());
        if (0 == uint256(entries.size())) {
            return (-1, "");
        } else {
            Entry entry = entries.get(0);
            return (0, entry.getString("asset_value"));
        }
    }

    function register(string memory fileId, string memory fileMD5) public returns(int256){
        int256 ret_code = 0;
        int256 ret= 0;
        // 查询文件是否存在
        (ret, ) = select(fileId);
        if(ret != 0) {
            Table table = openTable();
            Entry entry = table.newEntry();
            entry.set("file_id", fileId);
            entry.set("file_md5", fileMD5);
            // 插入
            int count = table.insert(fileId, entry);
            if (count == 1) {
                // 成功
                ret_code = 0;
            } else {
                // 失败? 无权限或者其他错误
                ret_code = -2;
            }
        } else {
            // 账户已存在
            ret_code = -1;
        }

        emit RegisterEvent(ret_code, fileId, fileMD5);

        return ret_code;
    }
}
