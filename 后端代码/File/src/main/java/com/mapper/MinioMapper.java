package com.mapper;

import com.pojo.FileInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MinioMapper {
    boolean addFileInfo(FileInfo fileInfo);
}
