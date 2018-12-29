package com.advance.Engine_and_Message.util;

import java.util.Collection;

/**
 * @Auther: 谷天乐
 * @Date: 2018/11/8 09:00
 * @Description:
 */
public class NonUtil {
    /*    */   public NonUtil() {}
    /*    */
/*  8 */   public static boolean isNon(Object object) { boolean isnon = false;
/*    */
/* 10 */     if (object == null) {
/* 11 */       return true;
/*    */     }
/* 13 */     if ((object instanceof String)) {
/* 14 */       String str = (String)object;
/* 15 */       if (str.trim().length() == 0)
/* 16 */         isnon = true;
/* 17 */     } else if ((object instanceof Collection)) {
/* 18 */       Collection collection = (Collection)object;
/* 19 */       if (collection.size() == 0)
/* 20 */         isnon = true;
/* 21 */     } else if ((object instanceof java.util.Map)) {
/* 22 */       java.util.Map map = (java.util.Map)object;
/* 23 */       if (map.size() == 0) {
/* 24 */         isnon = true;
/*    */       }
/*    */     }
/* 27 */     return isnon;
/*    */   }
    /*    */
/*    */   public static boolean isNotNon(Object object) {
/* 31 */     return !isNon(object);
/*    */   }
/*    */ }