import {Tag} from "./tag";

export interface Image{
  id:number;
  height:number;
  service:string;
  uploadTime:Date;
  url:string;
  width:number;
  tags: Tag[];

}
