package org.jbit.controller;

import com.alibaba.fastjson.JSON;
import org.jbit.entity.Position;
import org.jbit.service.PositionService;
import org.jbit.service.impl.PositionServiceImpl;
import org.jbit.vo.ResultVo;
import org.jbit.vo.SelectVo;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * 职务控制器
 * @author yh
 * @version 1.0,2020-11-27
 *
 */
@WebServlet("/position")
public class PositionController extends BaseController{
    //StaffService staffService = new StaffServiceImpl();
    /**
     * 注入Service
     */
    PositionService positionService = new PositionServiceImpl();

    private void getAllPositions(HttpServletRequest req, HttpServletResponse res) throws Exception {

        //获取职务数据
        List<Position> positions = positionService.getAllPositions();

        List<SelectVo> data =new ArrayList<>();
        for(Position position : positions) {
            SelectVo vo = new SelectVo(
                    position.getPositionId().toString(),
                    position.getPositionName());
            data.add(vo);
        }
        //封装到ResultVo
        ResultVo resultVo = new ResultVo(data);
        //响应结果
        String json = JSON.toJSONString(resultVo);
        PrintWriter out = res.getWriter();
        out.print(json);
    }

}
