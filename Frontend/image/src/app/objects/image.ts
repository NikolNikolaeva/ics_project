import {Tag} from "./tag";

export interface Image{
  id:number;
  height:number;
  service:string;
  uploaded_time:Date;
  url:string;
  width:number;
  tags: Tag[];

}
