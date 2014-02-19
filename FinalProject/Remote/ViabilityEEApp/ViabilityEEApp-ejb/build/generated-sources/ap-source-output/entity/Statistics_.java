package entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.1.v20130918-rNA", date="2014-01-31T17:57:22")
@StaticMetamodel(Statistics.class)
public class Statistics_ { 

    public static volatile SingularAttribute<Statistics, Long> id;
    public static volatile SingularAttribute<Statistics, Double> averageageusers;
    public static volatile SingularAttribute<Statistics, Double> averageweek;
    public static volatile SingularAttribute<Statistics, Date> tmstampedit;
    public static volatile SingularAttribute<Statistics, Double> averagemonth;
    public static volatile SingularAttribute<Statistics, Double> averagemonthhighpriority;

}