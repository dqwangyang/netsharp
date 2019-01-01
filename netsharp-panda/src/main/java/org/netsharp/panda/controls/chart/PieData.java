package org.netsharp.panda.controls.chart;

import java.math.BigDecimal;


/// <summary>
/// 饼图数据项
/// </summary>
public class PieData
{
  /// <summary>
  /// 名称(饼图会自动设置颜色)
  /// </summary>
  public String Name;

  /// <summary>
  /// 数值(饼图会自动计算百分比)
  /// </summary>
  public BigDecimal Value;

  /// <summary>
  /// 是否弹出
  /// </summary>
  public boolean IsPie;
}
