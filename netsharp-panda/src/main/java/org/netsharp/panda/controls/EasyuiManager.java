package org.netsharp.panda.controls;

import java.lang.reflect.Field;
import java.util.HashMap;

import org.netsharp.core.convertor.TypeConvertorFactory;
import org.netsharp.panda.annotation.DataOption;
import org.netsharp.panda.annotation.DataOptionAnnotation;
import org.netsharp.panda.annotation.EditorOption;
import org.netsharp.panda.annotation.EditorOptionAnnotation;
import org.netsharp.panda.annotation.HtmlAttr;
import org.netsharp.panda.annotation.HtmlAttrAnnotation;


public class EasyuiManager
{
    public static ControlPropertyMap Find(Class<?> controlTpye)
    {
        ControlPropertyMap map = conrolProperties.get(controlTpye);
        if (map!=null)
        {
            return map;
        }

        map = new ControlPropertyMap();

        Field[] fields = controlTpye.getFields();

        for (Field field : fields)
        {
        	HtmlAttr att = (HtmlAttr) field.getAnnotation(HtmlAttr.class);
            if (att != null)
            {
            	HtmlAttrAnnotation ha=new HtmlAttrAnnotation();
            	{
            		ha.html=att.html();
            		ha.defaultValue=att.defaultValue();
            		ha.mustWrite=att.mustWrite();
            		ha.name=att.name();
            		ha.serializeType=att.serializeType();
            	}
            	
            	ha.TypeConvertor=TypeConvertorFactory.create(field.getType());
            	ha.Field = field;
                map.HtmlProperties.add(ha);
            }

            DataOption att2 = (DataOption) field.getAnnotation(DataOption.class);
            if (att2 != null)
            {
            	DataOptionAnnotation ha=new DataOptionAnnotation();
            	{
            		ha.html=att2.html();
            		ha.defaultValue=att2.defaultValue();
            		ha.mustWrite=att2.mustWrite();
            		ha.name=att2.name();
            		ha.serializeType=att2.serializeType();
            		ha.isEvent=att2.isEvent();
            	}

            	ha.TypeConvertor=TypeConvertorFactory.create(field.getType());
            	ha.Field = field;
                map.DataOptionProperties.add(ha);
            }

            EditorOption att3 = (EditorOption) field.getAnnotation(EditorOption.class);
            if (att3 != null)
            {
            	EditorOptionAnnotation ha=new EditorOptionAnnotation();
            	{
            		ha.html=att3.html();
            		ha.defaultValue=att3.defaultValue();
            		ha.mustWrite=att3.mustWrite();
            		ha.name=att3.name();
            		ha.serializeType=att3.serializeType();
            		
            		ha.isEvent=att3.isEvent();
            		ha.isOption=att3.isOption();
            	}
            	ha.TypeConvertor=TypeConvertorFactory.create(field.getType());
            	ha.Field = field;
                map.EditorOptionProperties.add(ha);
            }
        }

        conrolProperties.put(controlTpye, map);

        return map;
    }

    static HashMap<Class<?>, ControlPropertyMap> conrolProperties = new HashMap<Class<?>, ControlPropertyMap>();
}

