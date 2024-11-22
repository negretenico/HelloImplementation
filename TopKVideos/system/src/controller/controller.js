import { throughDate } from "../filter/throughDate.js";
import { topK } from "../filter/topK.js"

export const controller = (data,k,startDate,endDate)=>{
    const throughDates =throughDate(data,startDate,endDate);
    return topK(throughDates,k);
}