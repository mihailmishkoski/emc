import React from "react";
import AuthorTerm from "../AuthorTerm/authorTerm";


const authors = (props) => {
    return (
        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Author Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.authors.map((term) => {
                            return (
                                <AuthorTerm term={term}/>
                            );
                        })}
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    );
}

export default authors;
