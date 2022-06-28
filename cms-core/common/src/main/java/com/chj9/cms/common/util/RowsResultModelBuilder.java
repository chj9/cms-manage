package com.chj9.cms.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.chj9.cms.common.vo.PageInfo;
import com.chj9.cms.common.vo.RowsResultModel;
import org.springframework.data.domain.Page;

import java.util.List;

public class RowsResultModelBuilder {
	 /**
     * IPage => RowsResultModel
     * @param iPage
     * @return
     */
    public static <T> RowsResultModel<T> of(IPage<T> iPage){
        return new RowsResultModel<>(
                iPage.getRecords(),
                new PageInfo((int)iPage.getTotal(),(int)iPage.getCurrent(),(int)iPage.getSize())
        );
    }

    public static <T>RowsResultModel<T> of(List<T> records,PageInfo pageInfo){
        return new RowsResultModel<>(
                records,pageInfo
        );
    }
    
    public static <T>RowsResultModel<T> of(Page<T> page){
        return new RowsResultModel<>(
        		page.getContent(),
                new PageInfo((int)page.getTotalElements(),page.getNumber(),page.getSize())
        );
    }

    /**
     * 辅助provider的vo对拷
     * @param iPage 原始iPage
     * @param records 替换集合（将替换原始iPage的集合）
     * @param <A> 原始IPage范型参数类型
     * @param <B> 最终返回的范参类型
     * @return
     */
    public static <A,B>RowsResultModel<B> of(IPage<A> iPage, List<B> records){
        return new RowsResultModel<B>(
                records,
                new PageInfo((int)iPage.getTotal(),(int)iPage.getCurrent(),(int)iPage.getSize())
        );
    }

//    public static <A,B>RowsResultModel<B> of(IPage<A> iPage, Class<B> outputType){
//        List<B> list = null;
//        if(iPage.getRecords()!=null){
//            list = iPage.getRecords().stream().map(a->DozerWrapper.copyAs(a,outputType)).collect(Collectors.toList());
//        }
//        return new RowsResultModel<>(
//                list,
//                new PageInfo((int)iPage.getTotal(),(int)iPage.getCurrent(),(int)iPage.getSize())
//        );
//    }
}

