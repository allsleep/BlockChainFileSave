// SPDX-License-Identifier: Apache-2.0
pragma solidity ^0.6.0;
pragma experimental ABIEncoderV2;

import "./KVTable.sol";

contract TestKV {
    KVTable kv_table;

    event SetEvent(int256 count);

    string constant TABLE_NAME = "tb_file_blockchain";

    constructor() public {
        kv_table = KVTable(0x1009);
        kv_table.createTable(TABLE_NAME, "file_id", "file_md5");
    }

    function get(string memory fileId) public view returns (bool, string memory) {
        bool ok = false;
        Entry memory entry;
        (ok, entry) = kv_table.get(TABLE_NAME, fileId);
        string memory fileMD5;
        if (ok) {
            fileMD5 = entry.fields[0].value;
        }
        return (ok, fileMD5);
    }

    function set(string memory fileId, string memory fileMD5) public returns (int256) {
        KVField memory kv1 = KVField("file_md5", fileMD5);
        KVField[] memory KVFields = new KVField[](1);
        KVFields[0] = kv1;
        Entry memory entry = Entry(KVFields);

        // the second parameter length of set should <= 255B
        int256 count = kv_table.set(TABLE_NAME, fileID, entry);
        emit SetEvent(count);
        return count;
    }
}
