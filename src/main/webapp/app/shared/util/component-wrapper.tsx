import React from "react";
import { useParams } from "react-router-dom";

export const withRouter = (Component) => {
  return (props) => {
    const params = useParams();
    return <Component {...props} params={params} />
  }
}