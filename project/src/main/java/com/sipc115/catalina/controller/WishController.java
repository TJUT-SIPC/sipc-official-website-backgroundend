package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.WishVO.WishListInfoVO;
import com.sipc115.catalina.VO.WishVO.WishListVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Wishes;
import com.sipc115.catalina.enums.WishStatusEnum;
import com.sipc115.catalina.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController

@RequestMapping("/")
public class WishController {

    @Autowired
    private WishService wishService;

    /**
     * 1.查询所有寄语
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    @GetMapping("/wishCenter/getAllWishes")
    public ResultVO getWishesInCnter_ADMIN(@RequestParam("page") Integer page,@RequestParam("pageSize") Integer pageSize,
                                     @RequestParam("status")Integer status){

        //控制一页显示的条数
        if(pageSize<5) pageSize = 5;
        if(pageSize>100) pageSize = 100;

        List<Wishes> wishList = new ArrayList();

        //1.分页查询所有寄语,检查要查询的状态代码
        if(status== WishStatusEnum.FAILED.getCode() || status==WishStatusEnum.DEFAULT.getCode() ||
                status==WishStatusEnum.CHECKED.getCode() || status==WishStatusEnum.PUBLISH.getCode()){
            wishList = wishService.findWishesByWishStatus(status,page-1,pageSize);
        }else if(status == 4){
            wishList = wishService.findAll(page-1,pageSize);
        }


        //2.数据组装

        WishListVO wishListVO = new WishListVO();

        List<WishListInfoVO> wishListInfoVOList = new ArrayList();

        for(Wishes wish : wishList){

            WishListInfoVO wishListInfoVO = new WishListInfoVO();

            //传入id，name，word
            wishListInfoVO.setWishId(wish.getWishId());
            wishListInfoVO.setWishName(wish.getWishName());
            wishListInfoVO.setWishWord(wish.getWishWord());
            wishListInfoVO.setWishStatus(wish.getWishStatus());

            wishListInfoVOList.add(wishListInfoVO);
        }

        wishListVO.setWishListInfoVOList(wishListInfoVOList);
        wishListVO.setTotal_wishes(wishListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(wishListVO);

        return resultVO;

    }

    /**
     * 2.修改寄语审核状态
     * @param id        寄语id
     * @param status    审核状态
     * @return
     */
    @PostMapping("/wishCenter/modifyWish")
    public ResultVO modifyWish_ADMIN(Integer id , Integer status){

        if(status>=WishStatusEnum.FAILED.getCode() && status<=WishStatusEnum.PUBLISH.getCode()){
            wishService.updateWishStatus(id, status);
            return new ResultVO(0,"success");
        }

        return new ResultVO(1,"状态码无效");

    }

    /**
     * 3.删除一条寄语
     * @param id    寄语id
     * @return      ResultVO
     */
    @PostMapping("/wishCenter/delWish")
    public ResultVO delWish_ADMIN(Integer id){
        wishService.delWish(id);
        return new ResultVO(0,"success");
    }

}
