import React, {useState} from "react";
import BookTerm from "../BookTerm/bookTerm";
import { Link } from "react-router-dom";
import EshopService from "../../../repository/eshop.Repository";

const Books = (props) => {


    const handleSearchChange = (event) => {

        props.onSearchBooks(event.target.value)

    };

    return (

        <div className={"container mm-4 mt-5"}>
            <div className={"row"}>
                {/* Search Input */}
                <div className="row">
                    <input
                        type="text"
                        onChange={handleSearchChange}
                        placeholder="Search books..."
                    />
                </div>
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
