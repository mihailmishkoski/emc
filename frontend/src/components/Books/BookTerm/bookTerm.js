import React from 'react';
import {Link} from "react-router-dom";

const BookTerm = (props) => {
    const { id, bookName, bookCategory, authorName, availableCopies, available } = props.term;

    return (
        <tr>
            <td>{bookName}</td>
            <td>{bookCategory}</td>
            <td>{authorName}</td>
            <td>{availableCopies}</td>
            <td>{available ? 'Available' : 'Not Available'}</td>
            <td className="text-right">
                <a className="btn btn-danger" onClick={() => props.onDelete(id)}>Delete</a>
                <a className="btn btn-dark" onClick={() => props.onOutOfStock(id)}>Mark as out of stock</a>
                <Link className={"btn btn-info ml-2"}
                      onClick={() => props.onEditBook(id)}
                      to={`/books/edit/${props.term.id}`}>
                    Edit
                </Link>


            </td>
        </tr>
    );
}

export default BookTerm;
