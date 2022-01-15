package com.mapper;

import com.pojo.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface MinioMapper {
    boolean addFileInfo(FileInfo fileInfo);
    List<FileInfo> findFileInfoByAccountID(String accountId);
}
