export const throughDate = (data,startDate,endDate)=>{
    const start = new Date(startDate);
    const end = new Date(endDate)
    return data.filter(record => {
        const recordDate = new Date(record.time);
        return recordDate >= start && recordDate <= end;
    });
}