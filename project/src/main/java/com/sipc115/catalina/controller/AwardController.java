package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.AwardVO.AwardListInfoVO;
import com.sipc115.catalina.VO.AwardVO.AwardListVO;
import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Awards;
import com.sipc115.catalina.dataobject.UserAndAward;
import com.sipc115.catalina.service.AwardService;
import com.sipc115.catalina.service.UserAndAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/awardCenter")
public class AwardController {

    @Autowired
    private AwardService awardService;
    @Autowired
    private UserAndAwardService userAndAwardService;

    /**
     * 1.分页查询所有奖项
     * @param page      当前页数
     * @param pageSize  一页显示多少条
     * @return
     */
    @PostMapping("/getAllAwards")
    @LoginRequired
    public ResultVO getAllAwards(Integer page, Integer pageSize){

        //日期格式化 yyyy/MM
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM");

        //1.分页查询所有奖项
        List<Awards> awardList = awardService.findAll(page-1, pageSize);

        //2.数据组装
        AwardListVO awardListVO = new AwardListVO();

        List<AwardListInfoVO> awardListInfoVOList = new ArrayList();

        for(Awards award : awardList){
            //传入id，name，time
            AwardListInfoVO awardListInfoVO = new AwardListInfoVO();
            awardListInfoVO.setAwardId(award.getAwardId());
            awardListInfoVO.setAwardName(award.getAwardName());
            awardListInfoVO.setAwardTime(sdf.format(award.getAwardTime()));

            awardListInfoVOList.add(awardListInfoVO);
        }

        awardListVO.setAwardListInfoVOList(awardListInfoVOList);
        awardListVO.setTotalAward(awardListInfoVOList.size());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(awardListVO);

        return resultVO;
    }

    /**
     * 2.添加一个奖项
     * @param name     奖项名称
     * @param time     获奖时间
     * @return         ResultVO
     */
    @PostMapping("/addAward")
    @LoginRequired
    public ResultVO addAward(String name, Date time){

        //1.验证参数
        boolean rightName = (name != null) && !name.trim().isEmpty() && name.length()<=50;

        if(rightName && time!=null){
            //2.封装对象
            Awards award = new Awards();
            award.setAwardName(name);
            award.setAwardTime(time);

            awardService.addAward(award);

            return new ResultVO(0,"success");
        }

        if(!rightName){
            return new ResultVO(1,"名字为空或长度超过上限");
        }

        return new ResultVO(2,"获奖时间不能为空");
    }

    /**
     * 3.修改一个奖项
     * @param id        奖项id
     * @param name      奖项名称
     * @param time      获奖时间
     * @return          ResultVO
     */
    @PostMapping("/modifyAward")
    @LoginRequired
    public ResultVO modifyAward(Integer id, String name , Date time){

        //1.验证参数
        boolean rightName = (name != null) && !name.trim().isEmpty() && name.length()<=50;

        if(rightName && time!=null){
            //2.封装对象
            Awards award = new Awards();
            award.setAwardId(id);
            award.setAwardName(name);
            award.setAwardTime(time);

            awardService.updateAward(award);

            return new ResultVO(0,"success");
        }

        if(!rightName){
            return new ResultVO(1,"名字为空或长度超过上限");
        }

        return new ResultVO(2,"获奖时间不能为空");
    }

    /**
     * 4.删除一个奖项
     * @param id    奖项id
     * @return
     */
    @PostMapping("/delAward")
    @LoginRequired
    public ResultVO delAward(Integer id){

        userAndAwardService.delRelationByAwardId(id);
        awardService.delAward(id);

        return new ResultVO(0,"success");
    }
}
