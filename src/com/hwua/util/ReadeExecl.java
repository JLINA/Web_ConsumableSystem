package com.hwua.util;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;

import com.hwua.pojo.Material;

/**
 * @author Geloin
 * 
 */
public class ReadeExecl {

	/**
	 * @param args
	 */
	public List<Material> reade(String url) throws Exception {

		String filePath = url;

		POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(filePath));

		HSSFWorkbook workBook = new HSSFWorkbook(fs);

		HSSFSheet sheet = workBook.getSheetAt(0);

		Iterator<Row> it = sheet.rowIterator();

		ArrayList<Material> list = new ArrayList<>();

		int x = 0;

		while (it.hasNext()) {

			Row row = it.next();

			int cellCount = row.getPhysicalNumberOfCells();

			if (x == 0) {
			} else {

			//	System.out.print("第" + x + "行：");

				Material material = new Material();

				for (int i = 0; i < cellCount; i++) {

					Cell cell = row.getCell(i);

					String cellFormatValue = (String) getCellFormatValue(cell);

			//		System.out.print(cellFormatValue + "  ");

					if (i == 0) {
						material.setNumbers(cellFormatValue);
					} else if (i == 1) {
						material.setTypeid(cellFormatValue);

					} else if (i == 2) {
						material.setName(cellFormatValue);
					} else if (i == 3) {
						material.setType(cellFormatValue);
					} else if (i == 4) {
						material.setStandstandard(cellFormatValue);
					} else if (i == 5) {
						material.setPrice(Double.parseDouble(cellFormatValue));
					} else if (i == 6) {
						material.setFactory(cellFormatValue);
					} else if (i == 7) {
						material.setLeavefactorydate(cellFormatValue);
					} else if (i == 8) {
						material.setBuydate(cellFormatValue);
					} else if (i == 9) {
						material.setIndate(cellFormatValue);
					} else if (i == 10) {
						material.setPlace(cellFormatValue);
					} else if (i == 11) {
						material.setUsesituation(cellFormatValue);
					} else if (i == 12) {
						material.setCount(Integer.parseInt(cellFormatValue));
					} else if (i == 13) {
						material.setSurplus(Integer.parseInt(cellFormatValue));
					} else if (i == 14) {
						material.setRemarks(cellFormatValue);
						material.setImage("");
					}

				}
				list.add(material);
				
				System.out.println();

			}
			x++;

		}
		return list;

	}

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 */
	private String getCellFormatValue(Cell cell) {
		String cellvalue = "";
		if (cell != null) {

			// 判断当前Cell的Type
			switch (cell.getCellType()) {

			case HSSFCell.CELL_TYPE_NUMERIC: {
				// 假设为日期型
				Date date = cell.getDateCellValue();
				Calendar c = Calendar.getInstance();
				c.setTime(date);
				if (DateUtil.isCellDateFormatted(cell) && String.valueOf(c.get(Calendar.YEAR)).length() == 4) {

					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);

				} else {
					cellvalue = String.valueOf(cell.getNumericCellValue());

					if (!cellvalue.matches("\\d+\\.\\d+")) {
						// 如果不是double，则转一下 以便统一格式
						BigDecimal big = new BigDecimal(cell.getNumericCellValue());
						cellvalue = String.valueOf(big.longValue());

					}
					if (cellvalue.contains(".0") && (!cellvalue.contains(".01")) && (!cellvalue.contains(".02"))
							&& (!cellvalue.contains(".03")) && (!cellvalue.contains(".04"))
							&& (!cellvalue.contains(".05")) && (!cellvalue.contains(".06"))
							&& (!cellvalue.contains(".07")) && (!cellvalue.contains(".08"))
							&& (!cellvalue.contains(".09"))) {

						cellvalue = cellvalue.replace(".0", "");

					}
				}

				break;
			}
				// 如果当前Cell的Type为STRIN
			case HSSFCell.CELL_TYPE_STRING:
				// 取得当前的Cell字符串
				cellvalue = cell.getRichStringCellValue().getString();
				break;
			// 默认的Cell值
			default:
				cellvalue = " ";
			}
		} else {
			cellvalue = "";
		}

		return cellvalue;

	}

}