package com.sipc115.catalina.controller;

import com.sipc115.catalina.VO.ResultVO;
import com.sipc115.catalina.VO.UserVO.UserListInfoVO;
import com.sipc115.catalina.VO.UserVO.UserListVO;
import com.sipc115.catalina.annotation.LoginRequired;
import com.sipc115.catalina.dataobject.Users;
import com.sipc115.catalina.enums.ResultEnum;
import com.sipc115.catalina.enums.UserStatusEnum;
import com.sipc115.catalina.exception.BusinessException;
import com.sipc115.catalina.service.CheckUserStatusService;
import com.sipc115.catalina.service.UploadFileService;
import com.sipc115.catalina.service.UserAndAwardService;
import com.sipc115.catalina.service.UserService;
import com.sipc115.catalina.utils.URLUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/userCenter")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserAndAwardService userAndAwardService;
    @Autowired
    private UploadFileService uploadFileService;
    @Autowired
    private CheckUserStatusService checkUserStatusService;

    /**
     * 1.根据id查询一个用户
     * @param id
     * @return
     */
    @PostMapping("/getUserById")
    @LoginRequired
    public ResultVO getUserById_SPECIAL(@RequestParam("id") Integer id, HttpServletRequest request){

        //日期格式化 yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1.查询用户
        Users user = userService.findOne(id);

        //2.进行身份校验，管理员身份以上可以随意查询id，普通用户只能查询自己信息
        if(!checkUserStatusService.isAdmin()){
            if(request.getSession().getAttribute("userId") != user.getUserId()){
                return new ResultVO(400,"你没有权限");
            }
        }

        //3.数据组装
        //传入用户信息
        UserListInfoVO userListInfoVO = new UserListInfoVO();
        userListInfoVO.setUserId(user.getUserId());
        userListInfoVO.setUserName(user.getUserName());
        userListInfoVO.setUserPassword(user.getUserPassword());
        userListInfoVO.setUserStudentId(user.getUserStudentId());
        userListInfoVO.setUserAge(user.getUserAge());
        userListInfoVO.setUserGender(user.getUserGender());
        userListInfoVO.setUserPhone(user.getUserPhone());
        userListInfoVO.setUserEmail(user.getUserEmail());
        userListInfoVO.setUserCreateTime(sdf.format(user.getUserCreateTime()));
        userListInfoVO.setUserLastLogin((user.getUserLastLogin()!=null)? sdf.format(user.getUserLastLogin()):null);
        userListInfoVO.setUserStatus(user.getUserStatus());
        userListInfoVO.setUserRemark(user.getUserRemark());
        userListInfoVO.setUserHeadImage(URLUtil.getLocalhostURL(request) + user.getUserHeadImage());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(userListInfoVO);

        return resultVO;
    }

    /**
     * 2.根据用户名查询一个用户
     * @param name
     * @param request
     * @return
     */
    @PostMapping("/getUserByUsername")
    @LoginRequired
    public ResultVO getUserByUsername_ROOT(@RequestParam("name") String name, HttpServletRequest request){

        //日期格式化 yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //1.查询用户
        Users user = userService.findOneByUserName(name);

        //2.数据组装

        //传入用户信息
        UserListInfoVO userListInfoVO = new UserListInfoVO();
        userListInfoVO.setUserId(user.getUserId());
        userListInfoVO.setUserName(user.getUserName());
        userListInfoVO.setUserPassword(user.getUserPassword());
        userListInfoVO.setUserStudentId(user.getUserStudentId());
        userListInfoVO.setUserAge(user.getUserAge());
        userListInfoVO.setUserGender(user.getUserGender());
        userListInfoVO.setUserPhone(user.getUserPhone());
        userListInfoVO.setUserEmail(user.getUserEmail());
        userListInfoVO.setUserCreateTime(sdf.format(user.getUserCreateTime()));
        userListInfoVO.setUserLastLogin((user.getUserLastLogin()!=null)? sdf.format(user.getUserLastLogin()):null);
        userListInfoVO.setUserStatus(user.getUserStatus());
        userListInfoVO.setUserRemark(user.getUserRemark());
        userListInfoVO.setUserHeadImage(URLUtil.getLocalhostURL(request) + user.getUserHeadImage());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(userListInfoVO);

        return resultVO;
    }

    /**
     * 3.分页获取所有用户
     * @param page 当前查询页数
     * @param pageSize  一页显示多少条
     * @param status 查询特定类用户[0普通用户，1管理员，2超级管理员，3全部查询]
     * @param request
     * @return
     */
    @GetMapping("/getUsers")
    @LoginRequired
    public ResultVO getUsers_ROOT(@RequestParam("page")Integer page, @RequestParam("pageSize")Integer pageSize ,@RequestParam("status") Integer status, HttpServletRequest request){

        //日期格式化 yyyy-MM-dd HH:mm:ss
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        if(pageSize<5) pageSize=5;
        if(pageSize>100) pageSize=100;

        //1.分页查询所有用户
        Page<Users> userListPage;
        List<Users> userList;
        if(status >= UserStatusEnum.NORMAL.getCode() && status <=UserStatusEnum.ROOT.getCode()){
            userListPage = userService.findAllByUserStatus(status, page - 1, pageSize);
            userList = userListPage.getContent();
        }else{
            userListPage = userService.findAll(page - 1, pageSize);
            userList = userListPage.getContent();
        }

        //2.数据组装
        UserListVO userListVO = new UserListVO();

        List<UserListInfoVO> userListInfoVOList = new ArrayList();

        for(Users user : userList){
            //传入用户信息
            UserListInfoVO userListInfoVO = new UserListInfoVO();
            userListInfoVO.setUserId(user.getUserId());
            userListInfoVO.setUserName(user.getUserName());
            userListInfoVO.setUserPassword(user.getUserPassword());
            userListInfoVO.setUserStudentId(user.getUserStudentId());
            userListInfoVO.setUserAge(user.getUserAge());
            userListInfoVO.setUserGender(user.getUserGender());
            userListInfoVO.setUserPhone(user.getUserPhone());
            userListInfoVO.setUserEmail(user.getUserEmail());
            userListInfoVO.setUserCreateTime(sdf.format(user.getUserCreateTime()));
            userListInfoVO.setUserLastLogin((user.getUserLastLogin()!=null)? sdf.format(user.getUserLastLogin()):null);
            userListInfoVO.setUserStatus(user.getUserStatus());
            userListInfoVO.setUserRemark(user.getUserRemark());
            userListInfoVO.setUserHeadImage(URLUtil.getLocalhostURL(request) + user.getUserHeadImage());

            userListInfoVOList.add(userListInfoVO);
        }

        userListVO.setUserListInfoVOList(userListInfoVOList);
        userListVO.setTotal_users((int) userListPage.getTotalElements());

        /**返回ResultVO*/
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(0);
        resultVO.setMsg("success");
        resultVO.setData(userListVO);

        return resultVO;
    }


    /**
     * 4.添加一个用户
     * @param name          用户名(Y)
     * @param password      密码(Y)
     * @param student_id    学号(N)
     * @param age           年龄(N)
     * @param gender        性别(N)
     * @param phone         手机号(N)
     * @param email         邮箱(N)
     * @param status        权限(Y)
     * @param remark        备注(N)
     * @param head_image    头像(N)
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping("/addUser")
    @LoginRequired
    public ResultVO addUser_ROOT(String name, String password, String student_id, Integer age, String gender, String phone, String email, Integer status, String remark, String head_image) throws IOException {

        //1.验证必须参数
        boolean rightName = false;
        boolean rightPassword = false;

        String nameRegex = "^[\\u4e00-\\u9fff\\wa-zA-Z0-9_-]{5,13}$";     //描述：5到13位(汉子，字母，数字，下划线，减号)
        String passwordRegex = "^(\\w){6,20}$";         //6到20位(字母，数字，下划线)

        if (name!=null && name.matches(nameRegex)) rightName = true;
        if (password!=null && password.matches(passwordRegex)) rightPassword = true;

        //2.验证不必须参数
        boolean rightStudentId = false;
        boolean rightAge = false;
        boolean rightPhone = false;
        boolean rightEmail = false;

        String studentIdRegex = "^[0-9]{8}$";                   //8位数字
        String ageRegex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";   //1到120的整数
        String phoneRegex = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        String emailRegex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$";

        rightStudentId = (student_id == null) || student_id.matches(studentIdRegex);
        rightAge = (age == null) || age.toString().matches(ageRegex);
        rightPhone = (phone == null) || phone.matches(phoneRegex);
        rightEmail = (email == null) || email.matches(emailRegex);

        //3.保存数据
        if(rightName && rightPassword && rightStudentId && rightAge && rightPhone && rightEmail){

            //封装对象
            Users user = new Users();
            user.setUserName(name);
            user.setUserPassword(password);
            user.setUserStudentId(student_id);
            user.setUserAge(age);
            user.setUserGender(gender);
            user.setUserPhone(phone);
            user.setUserEmail(email);
            user.setUserStatus(status);
            user.setUserRemark(remark);
            user.setUserHeadImage(head_image);

            userService.addUser(user);

            return new ResultVO(0,"success");

        }
        if(!rightName){
            return new ResultVO(1,"用户名格式错误");
        }
        if(!rightPassword){
            return new ResultVO(2,"密码格式错误");
        }
        if(!rightStudentId){
            return new ResultVO(3,"学号格式错误");
        }
        if(!rightAge){
            return new ResultVO(4,"年龄超出范围");
        }
        if(!rightPhone){
            return new ResultVO(5, "手机号码格式错误");
        }
        if(!rightEmail){
            return new ResultVO(6,"邮箱格式错误");
        }

        return null;

    }


    /**
     * 5.通过id修改一个用户信息
     * @param id            要修改的用户id
     * @param name          修改后的用户名
     * @param password      修改后的密码
     * @param student_id    修改后的学号
     * @param age           修改后的年龄
     * @param gender        修改后的性别
     * @param phone         修改后的手机号
     * @param email         修改后的邮箱
     * @param status        修改后的权限
     * @param remark        修改后的备注
     * @param head_image    修改后的头像
     * @return ResultVO
     * @throws IOException
     */
    @PostMapping("/modifyUser")
    @LoginRequired
    public ResultVO modifyUser_SPECIAL(Integer id, String name, String password, String student_id, Integer age, String gender, String phone, String email, Integer status, String remark, String head_image, HttpServletRequest request) throws IOException {

        //1.验证必须参数

        boolean rightName = false;
        boolean rightPassword = false;

        String nameRegex = "^[\\u4e00-\\u9fff\\wa-zA-Z0-9_-]{5,13}$";     //描述：5到13位(汉子，字母，数字，下划线，减号)
        String passwordRegex = "^(\\w){6,20}$";         //6到20位(字母，数字，下划线)

        if (name!=null && name.matches(nameRegex)) rightName = true;
        if (password!=null && password.matches(passwordRegex)) rightPassword = true;
        if (status==null || status < UserStatusEnum.NORMAL.getCode() || status > UserStatusEnum.ROOT.getCode()) status = UserStatusEnum.NORMAL.getCode();

        //2.验证不必须参数
        boolean rightStudentId = false;
        boolean rightAge = false;
        boolean rightPhone = false;
        boolean rightEmail = false;

        String studentIdRegex = "^[0-9]{8}$";                   //8位数字
        String ageRegex = "^(?:[1-9][0-9]?|1[01][0-9]|120)$";   //1到120的整数
        String phoneRegex = "^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\\d{8}$";
        String emailRegex = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)*(\\.[a-z]{2,})$";

        rightStudentId = (student_id == null) || student_id.matches(studentIdRegex);
        rightAge = (age == null) || age.toString().matches(ageRegex);
        rightPhone = (phone == null) || phone.matches(phoneRegex);
        rightEmail = (email == null) || email.matches(emailRegex);

        //3.保存数据
        if(id!=null && rightName && rightPassword && rightStudentId && rightAge && rightPhone && rightEmail){

            //1.获取用户id
            Users user = userService.findOne(id);

            //2.进行身份校验，超级管理员身份以上可以随意修改用户信息
            if(!checkUserStatusService.isRoot()){
                if(!request.getSession().getAttribute("userId") .equals(user.getUserId())){
                    log.error("[修改用户信息]失败,没有权限修改他人信息,session_user_id={},update_user_id={}",request.getSession().getAttribute("userId"),user.getUserId());
                    return new ResultVO(400,"你没有权限修改其他用户");
                }
                if(!status.equals(user.getUserStatus())){
                    log.error("[修改用户权限]失败,你没有权限修改,username={},status={}",user.getUserName(),status);
                    return new ResultVO(401,"你没有权限修改用户权限");
                }
            }

            //若头像更新，删除原有头像图片资源
            if(head_image != null && !head_image.equals(user.getUserHeadImage())){
                uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + user.getUserHeadImage());
            }

            //封装对象
            user.setUserId(id);
            user.setUserName(name);
            user.setUserPassword(password);
            user.setUserStudentId(student_id);
            user.setUserAge(age);
            user.setUserGender(gender);
            user.setUserPhone(phone);
            user.setUserEmail(email);
            user.setUserStatus(status);
            user.setUserRemark(remark);
            user.setUserHeadImage(head_image);

            userService.updateUser(user);

            return new ResultVO(0,"success");

        }
        if(rightName == false){
            log.error("用户名格式错误,username={}",name);
            return new ResultVO(1,"用户名格式错误");
        }
        if(rightPassword == false){
            log.error("密码格式错误,password={}",password);
            return new ResultVO(2,"密码格式错误");
        }
        if(rightStudentId == false){
            log.error("学号格式错误,studentId={}",student_id);
            return new ResultVO(3,"学号格式错误");
        }
        if(rightAge == false){
            log.error("年龄超出范围,age={}",age);
            return new ResultVO(4,"年龄超出范围");
        }
        if(rightPhone == false){
            log.error("手机号格式错误,phone={}",phone);
            return new ResultVO(5, "手机号码格式错误");
        }
        if(rightEmail == false){
            log.error("邮箱格式错误,email={}",email);
            return new ResultVO(6,"邮箱格式错误");
        }
        if(id == null){
            log.error("传入的用户id为空,user_id={}",id);
            return new ResultVO(7,"传入的用户id为空");
        }

        return null;

    }


    /**
     * 6.通过id删除一个用户
     * @param id 要删除的用户id
     * @return
     */
    @PostMapping("/delUser")
    @LoginRequired
    public ResultVO delUser_ROOT(Integer id){

        if(id == null){
            log.error("传入用户id为空,id={}",id);
            throw new BusinessException(ResultEnum.PARAM_ERROR);
        }

        //1.先删除用户关联的奖项记录
        userAndAwardService.delRelationByUserId(id);

        //2.删除用户头像图片资源
        Users user = userService.findOne(id);
        uploadFileService.deleteImage(URLUtil.getVirtualLocalhostPath() + user.getUserHeadImage());

        //3.从数据库删除记录
        userService.delUser(id);
        return new ResultVO(0,"success");

    }

}
