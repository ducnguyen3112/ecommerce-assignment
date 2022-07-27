import React from 'react';
import {useParams} from "react-router-dom";

function Pagination() {
    return (
        <nav aria-label="navigation example center">
            <ul className="pagination">
                <li className="page-item"><a className="page-link" href="#">Previous</a>
                </li>
                <li className="page-item"><a className="page-link" href="#">1</a></li>
                <li className="page-item"><a className="page-link" href="#">2</a></li>
                <li className="page-item"><a className="page-link" href="#">3</a></li>
                <li className="page-item"><a className="page-link" href="#">Next</a></li>
            </ul>
        </nav>
    );
}

export default Pagination;