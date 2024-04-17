import React from 'react';

const authorTerm = (props) => {
    return (
        <tr>
            <th scope={"col"}>{props.term.authorName}</th>
        </tr>
    )
}
export default authorTerm;
