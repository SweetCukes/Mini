package com.mooc.miniprogram.dao;

import com.mooc.miniprogram.entity.Area;

import java.util.List;

/**
 * @author SweetC
 * @Date 2021/6/4
 */
public interface AreaDao {

    List<Area> queryArea();

    Area queryAreaById(int areaId);

    int insertArea(Area area);

    int updateArea(Area area);

    int deleteArea(int areaId);
}
