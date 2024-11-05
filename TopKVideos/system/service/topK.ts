import { Video } from "../types/type";

export const topK = (data: Array<Video>,k:number) : Array<Video>=>{
    const localData = JSON.parse(JSON.stringify(data))
    return localData.splice(-k)
}