package com.pengyue.ptas.dao.xt;

import java.util.List;

import com.pengyue.ptas.bean.Dictionary;
import com.pengyue.ptas.bean.DictionaryValue;
import com.pengyue.ptas.bean.XtJS;
import com.pengyue.ptas.util.PageInfo;
import com.pengyue.ptas.util.ResultListInfo;
import com.pengyue.ptas.util.TypecodeAndValue;

public interface DictionaryDao {
	public int insertDic(Dictionary dic);
	public int insertValue(DictionaryValue dicValue);
	public int updateDic(Dictionary dic);
	public int updateValue(DictionaryValue dicValue);
	public int deleteDic(List<String> ids);
	public int deleteValue(List<String> ids);
    public List<Dictionary> getAll();
    public List<DictionaryValue> selectValueByDicId(String dicId);
    public List<DictionaryValue> getAllValuesByCode(String typeCode);
    public DictionaryValue getValuesByCodeAndValue(TypecodeAndValue tav);
}