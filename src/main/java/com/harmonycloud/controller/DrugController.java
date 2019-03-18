package com.harmonycloud.controller;

import com.harmonycloud.dto.DrugIntegerList;
import com.harmonycloud.entity.Drug;
import com.harmonycloud.result.CimsResponseWrapper;
import com.harmonycloud.service.DrugFavouriteGroupService;
import com.harmonycloud.service.DrugService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author qidong
 * @date 2019/3/4
 */

@Api(tags = "Drug")
@RestController
public class DrugController {

    @Autowired
    DrugService drugService;

    @Autowired
    DrugFavouriteGroupService drugFavouriteGroupService;

    @ApiOperation(value = "get drug list", httpMethod = "GET")
    @ApiImplicitParam(name = "keyword", value = "keyword", paramType = "query", dataType = "String")
    @GetMapping("/drugList")
    public CimsResponseWrapper searchByKeyword(@RequestParam("keyword") String keyword) throws Exception{
        return drugService.getDrugList(keyword);
    }

    @ApiOperation(value = "get department favourite drug list", httpMethod = "GET")
    @ApiImplicitParam(name = "clinicId", value = "clinicId", paramType = "query", dataType = "Integer")
    @GetMapping("/depFavList")
    public CimsResponseWrapper getDepFavList(Integer clinicId) throws Exception{
        return drugFavouriteGroupService.getDepFavList(clinicId);
    }


    @PostMapping("/drug")
    public CimsResponseWrapper getDrug(@RequestBody Integer[] drugIdList) throws Exception{
        List<Drug> drugList = drugService.getDrugListByIntegerList(drugIdList);
        return CimsResponseWrapper.buildSuccess(drugList);
    }
}
