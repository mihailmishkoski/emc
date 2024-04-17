import React from "react";
import BookTerm from "../BookTerm/bookTerm";
import { Link, useHistory } from "react-router-dom";

const Books = (props) => {
    /*const history = useHistory();

    const onFormSubmit = (e) => {
        e.preventDefault();
        const bookName = e.target.elements.q.value;
        props.searchBook(bookName);
        history.push("/books");
    };*/

    return (
        <div className={"container mm-4 mt-5"}>
            {/*<div className={"row"}>
                <form onSubmit={onFormSubmit}>
                    <label htmlFor="searchInput">Search:</label>
                    <input type="text" id="searchInput" name="q" placeholder="Enter your search query" />
                    <button type="submit">Search</button>
                </form>
            </div>*/}
            <div className={"row"}>
                <div className={"row"}>
                    <table className={"table table-striped"}>
                        <thead>
                        <tr>
                            <th scope={"col"}>Name</th>
                        </tr>
                        </thead>
                        <tbody>
                        {props.books.map((term) => (
                            <BookTerm
                                key={term.id}
                                term={term}
                                onDelete={props.onDelete}
                                onOutOfStock={props.onOutOfStock}
                                onEditBook={props.onEdit}
                               /* onSearchBook={props.onSearchBooks}*/
                            />
                        ))}
                        </tbody>
                    </table>
                </div>
                <div className="col mb-3">
                    <div className="col-sm-12 col-md-12">
                        <div className="col-sm-12 col-md-12">
                            <Link className="btn btn-block bn-darkpm" onClick={() => props.onAddBook} to={"/books/add"}>
                                Add new book
                            </Link>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Books;
