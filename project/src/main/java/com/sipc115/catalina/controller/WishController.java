package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.WishVO.WishListInfoVO;
import com.sipc115.catalina.VO.WishVO.WishListVO;
import com.sipc115.catalina.dataobject.Wishes;
import com.sipc115.catalina.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class WishController {

    @Autowired
    private WishService wishService;

    @GetMapping("/wishes")
    public ResultVO getWishes(){

        //1.随机查询15条已发布寄语
        List<Wishes> wishList = wishService.findWishesByLimit(15);

        //2.数据组装

        WishListVO wishListVO = new WishListVO();

        List<WishListInfoVO> wishListInfoVOList = new ArrayList();

        for(Wishes wish : wishList){

            WishListInfoVO wishListInfoVO = new WishListInfoVO();

            //传入id，name，word
            wishListInfoVO.setWishId(wish.getWishId());
            wishListInfoVO.setWishName(wish.getWishName());
            wishListInfoVO.setWishWord(wish.getWishWord());

            wishListInfoVOList.add(wishListInfoVO);
        }

        wishListVO.setWishListInfoVOList(wishListInfoVOList);

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(wishListVO);

        return resultVO;

    }

    @PostMapping("/sendWishes")
    public ResultVO sendWish(String name, String word){

        /**验证参数*/
        if(name!=null && !name.trim().isEmpty() && word!=null && !word.trim().isEmpty()){
            /**保存寄语*/
            Wishes wish = new Wishes();
            wish.setWishName(name);
            wish.setWishWord(word);
            wishService.addWish(wish);

            return new ResultVO(0,"success");
        }
        if(name == null || name.trim().isEmpty()){
            return new ResultVO(1,"人名不能为空");
        }
        if(word == null || word.trim().isEmpty()){
            return new ResultVO(2,"寄语不能为空");
        }

        return new ResultVO(0,"success");
    }

}
